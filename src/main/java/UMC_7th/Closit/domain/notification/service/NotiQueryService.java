package UMC_7th.Closit.domain.notification.service;

import UMC_7th.Closit.domain.notification.entity.Notification;
import org.springframework.data.domain.Slice;

public interface NotiQueryService {
    Slice<Notification> getNotificationList (Long userId, Integer page);
}
