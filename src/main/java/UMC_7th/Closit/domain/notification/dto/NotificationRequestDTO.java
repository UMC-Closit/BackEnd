package UMC_7th.Closit.domain.notification.dto;

import UMC_7th.Closit.domain.notification.entity.NotificationType;
import lombok.Getter;

public class NotificationRequestDTO {

    @Getter
    public static class SendNotiRequestDTO { // 알림 전송
        private Long userId;
        private String content;
        private NotificationType type;
    }
}