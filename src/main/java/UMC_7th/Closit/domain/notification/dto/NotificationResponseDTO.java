package UMC_7th.Closit.domain.notification.dto;

import UMC_7th.Closit.domain.notification.entity.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class NotificationResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class createNotiResultDTO {
        private Long notificationId;
        private Long userId;
        private String content;
        private NotificationType type;
    }
}