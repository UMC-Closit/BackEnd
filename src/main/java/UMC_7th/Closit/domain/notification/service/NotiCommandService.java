package UMC_7th.Closit.domain.notification.service;

import UMC_7th.Closit.domain.follow.entity.Follow;
import UMC_7th.Closit.domain.notification.dto.NotificationRequestDTO;
import UMC_7th.Closit.domain.notification.entity.Notification;
import UMC_7th.Closit.domain.post.entity.Comment;
import UMC_7th.Closit.domain.post.entity.Likes;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface NotiCommandService {
    SseEmitter subscribe (Long userId, String lastEventId); // SSE 연결
    Notification sendNotification (NotificationRequestDTO.SendNotiRequestDTO request); // SSE 알림 전송
    void commentNotification(Comment comment); // 댓글 알림
    void likeNotification(Likes likes); // 좋아요 알림
    void followNotification(Follow follow); // 팔로우 알림
}