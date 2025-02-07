package UMC_7th.Closit.domain.notification.service;

import UMC_7th.Closit.domain.notification.dto.NotificationRequestDTO;
import UMC_7th.Closit.domain.notification.entity.Notification;

public interface NotiCommandService {
    Notification sendNotification(NotificationRequestDTO.SendNotiRequestDTO request); // 알림 전송
}