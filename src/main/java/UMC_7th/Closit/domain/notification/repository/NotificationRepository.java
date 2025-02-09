package UMC_7th.Closit.domain.notification.repository;

import UMC_7th.Closit.domain.notification.entity.Notification;
import UMC_7th.Closit.domain.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Slice<Notification> findByUserOrderByCreatedAtDesc(User user, Pageable pageable); // 알림 목록 조회
}