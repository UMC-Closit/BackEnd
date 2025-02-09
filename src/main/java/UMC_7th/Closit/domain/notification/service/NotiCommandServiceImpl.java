package UMC_7th.Closit.domain.notification.service;

import UMC_7th.Closit.domain.follow.entity.Follow;
import UMC_7th.Closit.domain.notification.converter.NotificationConverter;
import UMC_7th.Closit.domain.notification.dto.NotificationRequestDTO;
import UMC_7th.Closit.domain.notification.entity.Notification;
import UMC_7th.Closit.domain.notification.entity.NotificationType;
import UMC_7th.Closit.domain.notification.repository.NotificationRepository;
import UMC_7th.Closit.domain.notification.repository.EmitterRepository;
import UMC_7th.Closit.domain.post.entity.Comment;
import UMC_7th.Closit.domain.post.entity.Likes;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.domain.user.repository.UserRepository;
import UMC_7th.Closit.global.apiPayload.code.status.ErrorStatus;
import UMC_7th.Closit.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class NotiCommandServiceImpl implements NotiCommandService {

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    private final EmitterRepository emitterRepository;

    @Override
    public SseEmitter subscribe(Long userId, String lastEventId) { // SSE 연결
        // 연결 지속 시간 = 1시간
        SseEmitter emitter = new SseEmitter(60 * 60 * 1000L);
        String emitterId = userId + "-" + System.currentTimeMillis();

        emitterRepository.save(emitterId, emitter);

        // 완료, 시간초과로 인한 전송 불가 -> SseEmitter 삭제
        emitter.onCompletion(() -> emitterRepository.deleteById(emitterId));
        emitter.onTimeout(() -> emitterRepository.deleteById(emitterId));

        sendToClient(emitter, emitterId, "SSE Connection Complete, EventStream Created. [userId=" + userId + "]");

        if (!lastEventId.isEmpty()) { // Last-Event-ID 존재 = 받지 못한 데이터 존재
            Map<String, Object> events = emitterRepository.findAllEventCacheByUserId(emitterId);
            events.entrySet().stream()
                    .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                    .forEach(entry -> sendToClient(emitter, entry.getKey(), entry.getValue()));
        }
        return emitter;
    }

    // SseEmitter = 최초 연결 시 생성, 해당 SseEmitter를 생성한 클라이언트에게 알림 전송
    public void sendToClient(SseEmitter emitter, String emitterId, Object data) {
        try {
            emitter.send(SseEmitter.event()
                    .id(emitterId)
                    .name("SSE")
                    .data(data));
        } catch (IOException exception) {
            emitterRepository.deleteById(emitterId);
            throw new GeneralException(ErrorStatus.SSE_CONNECT_FAILED);
        }
    }

    @Override
    public Notification sendNotification(NotificationRequestDTO.SendNotiRequestDTO request) { // 알림 전송
        User user = userRepository.findById(request.getReceiverId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        Notification notification = NotificationConverter.toNotification(user, request);
        notificationRepository.save(notification);

        // SSE 통한 알림 전송
        Map<String, SseEmitter> emitters = emitterRepository.findAllEmitterByUserId(request.getReceiverId().toString());

        emitters.forEach(
                (key, emitter) -> {
                    // 데이터 전송
                    sendToClient(emitter, key, NotificationConverter.sendNotiResult(notification));
                }
        );
        return notification;
    }

    @Override
    public void likeNotification(Likes likes) { // 좋아요 알림
        User receiver = likes.getPost().getUser(); // 게시글 작성자
        String content = likes.getUser().getName() + "님이 좋아요를 눌렀습니다. ";

        NotificationRequestDTO.SendNotiRequestDTO request = NotificationConverter.sendNotiRequest(receiver, content, NotificationType.LIKE);

        sendNotification(request);
    }

    @Override
    public void commentNotification(Comment comment) { // 댓글 알림
        User receiver = comment.getPost().getUser(); // 게시글 작성자 ID
        String content = comment.getUser().getName() + "님이 댓글을 작성했습니다. ";

        NotificationRequestDTO.SendNotiRequestDTO request = NotificationConverter.sendNotiRequest(receiver, content, NotificationType.COMMENT);

        sendNotification(request);
    }

    @Override
    public void followNotification(Follow follow) { // 팔로우 알림
        User receiver = follow.getFollowing(); // 팔로잉
        String content = follow.getFollower().getName() + "님이 회원님을 팔로우하기 시작했습니다. ";

        NotificationRequestDTO.SendNotiRequestDTO request = NotificationConverter.sendNotiRequest(receiver, content, NotificationType.FOLLOW);

        sendNotification(request);
    }

    @Override
    public Notification readNotification(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.NOTIFICATION_NOT_FOUND));

        // 읽음 처리
        notification.markAsRead();

        return notificationRepository.save(notification);
    }

    @Override
    public void deleteNotification(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.NOTIFICATION_NOT_FOUND));

        notificationRepository.deleteById(notificationId);
    }
}