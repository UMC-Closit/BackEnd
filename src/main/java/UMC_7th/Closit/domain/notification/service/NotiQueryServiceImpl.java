package UMC_7th.Closit.domain.notification.service;

import UMC_7th.Closit.domain.notification.entity.Notification;
import UMC_7th.Closit.domain.notification.repository.NotificationRepository;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.domain.user.repository.UserRepository;
import UMC_7th.Closit.global.apiPayload.code.status.ErrorStatus;
import UMC_7th.Closit.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NotiQueryServiceImpl implements NotiQueryService {

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    @Override
    public Slice<Notification> getNotificationList(Long userId, Integer page) { // 알림 목록 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        Pageable pageable = PageRequest.of(page, 10);

        // 최신순으로 조회
        Slice<Notification> notificationList = notificationRepository.findByUserOrderByCreatedAtDesc(user, pageable);

        // 전체 알림 읽음 처리
        notificationList.forEach(notification -> {
            notification.markAsRead();
            notificationRepository.save(notification);
        });

        return notificationList;
    }
}
