package UMC_7th.Closit.domain.notification.repository;

import UMC_7th.Closit.domain.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
