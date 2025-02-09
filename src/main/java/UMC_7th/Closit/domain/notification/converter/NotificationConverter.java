package UMC_7th.Closit.domain.notification.converter;

import UMC_7th.Closit.domain.notification.dto.NotificationRequestDTO;
import UMC_7th.Closit.domain.notification.dto.NotificationResponseDTO;
import UMC_7th.Closit.domain.notification.entity.Notification;
import UMC_7th.Closit.domain.notification.entity.NotificationType;
import UMC_7th.Closit.domain.user.entity.User;

public class NotificationConverter {

    public static Notification toNotification (User user, NotificationRequestDTO.SendNotiRequestDTO request) { // 알림 전송
        return Notification.builder()
                .user(user)
                .content(request.getContent())
                .type(request.getType())
                .build();
    }

    public static NotificationResponseDTO.SendNotiResultDTO sendNotiResult (Notification notification) {
        return NotificationResponseDTO.SendNotiResultDTO.builder()
                .notificationId(notification.getId())
                .content(notification.getContent())
                .type(notification.getType())
                .isRead(notification.isRead())
                .createdAt(notification.getCreatedAt())
                .build();
    }

    public static NotificationRequestDTO.SendNotiRequestDTO sendNotiRequest (User receiver, String content, NotificationType notificationType) {
        return NotificationRequestDTO.SendNotiRequestDTO.builder()
                .receiverId(receiver.getId())
                .content(content)
                .type(notificationType)
                .build();
    }
}