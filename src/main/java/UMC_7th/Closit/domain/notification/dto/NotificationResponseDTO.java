package UMC_7th.Closit.domain.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class NotificationResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SendNotiResultDTO { // 알림 전송
        private Long notificationId;
        private LocalDateTime createdAt;
    }
}