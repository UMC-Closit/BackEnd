package UMC_7th.Closit.domain.notification.controller;

import UMC_7th.Closit.domain.notification.converter.NotificationConverter;
import UMC_7th.Closit.domain.notification.dto.NotificationRequestDTO;
import UMC_7th.Closit.domain.notification.dto.NotificationResponseDTO;
import UMC_7th.Closit.domain.notification.entity.Notification;
import UMC_7th.Closit.domain.notification.service.NotiCommandService;
import UMC_7th.Closit.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/notifications")
@RequestMapping("/api/auth/notifications")
public class NotificationController {

    private final NotiCommandService notiCommandService;

    @Operation(summary = "SSE 연결")
    @GetMapping(value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter createNotification(@RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId,
                                                      // @AuthenticationPrincipal Authentication authentication,
                                                      @RequestParam Long userId) {

        SseEmitter sseEmitter = notiCommandService.subscribe(userId,lastEventId);

        return sseEmitter;
    }

    @Operation(summary = "알림 전송")
    @PostMapping
    public ApiResponse<NotificationResponseDTO.SendNotiResultDTO> createNotification(// Authentication authentication,
                                                                                     @RequestBody NotificationRequestDTO.SendNotiRequestDTO request) {

        // Long userId = Long.parseLong(authentication.getName());

        Notification sendNotification = notiCommandService.sendNotification(request);

        return ApiResponse.onSuccess(NotificationConverter.sendNotiResult(sendNotification));
    }

    @Operation(summary = "알림 목록 조회", description = "특정 사용자의 알림 목록 조회")
    @GetMapping()
    public ApiResponse<List<String>> getNotifications(@PathVariable Long user_id) {
        return ApiResponse.onSuccess(List.of("Notification1", "Notification2"));
    }

    @Operation(summary = "알림 삭제", description = "특정 알림을 삭제합니다.")
    @DeleteMapping("/{notification_id}")
    public ResponseEntity<String> deleteNotification(@PathVariable Long notification_id) {
        return ResponseEntity.ok("Deleted Notification with ID: " + notification_id);
    }
}