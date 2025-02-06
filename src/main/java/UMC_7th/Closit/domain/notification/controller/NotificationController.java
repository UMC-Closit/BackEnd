package UMC_7th.Closit.domain.notification.controller;

import UMC_7th.Closit.domain.notification.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Operation(summary = "알림 생성", description = "새로운 알림을 생성합니다.")
    @PostMapping
    public ResponseEntity<String> createNotification(@RequestBody String notification) {
        return ResponseEntity.ok("Created Notification: " + notification);
    }

    @Operation(summary = "알림 삭제", description = "특정 알림을 삭제합니다.")
    @DeleteMapping("/{notification_id}")
    public ResponseEntity<String> deleteNotification(@PathVariable Long notification_id) {
        return ResponseEntity.ok("Deleted Notification with ID: " + notification_id);
    }
}