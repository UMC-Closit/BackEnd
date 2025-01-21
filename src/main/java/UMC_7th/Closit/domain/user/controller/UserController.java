package UMC_7th.Closit.domain.user.controller;

import UMC_7th.Closit.domain.highlight.entity.Highlight;
import UMC_7th.Closit.domain.user.converter.UserConverter;
import UMC_7th.Closit.domain.user.dto.UserResponseDTO;
import UMC_7th.Closit.domain.user.service.UserCommandService;
import UMC_7th.Closit.domain.user.service.UserQueryService;
import UMC_7th.Closit.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/users")
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    @Operation(summary = "사용자의 팔로워 조회", description = "특정 사용자의 팔로워 목록을 조회합니다.")
    @GetMapping("/{user_id}/followers")
    public ResponseEntity<List<String>> getFollowers(@PathVariable Long user_id) {
        return ResponseEntity.ok(List.of("Follower1", "Follower2"));
    }

    @Operation(summary = "사용자의 팔로잉 조회", description = "특정 사용자의 팔로잉 목록을 조회합니다.")
    @GetMapping("/{user_id}/followings")
    public ResponseEntity<List<String>> getFollowings(@PathVariable Long user_id) {
        return ResponseEntity.ok(List.of("Following1", "Following2"));
    }

    @Operation(summary = "사용자의 알림 목록 조회", description = "특정 사용자의 알림 목록을 조회합니다.")
    @GetMapping("/{user_id}/notifications")
    public ResponseEntity<List<String>> getNotifications(@PathVariable Long user_id) {
        return ResponseEntity.ok(List.of("Notification1", "Notification2"));
    }

    @Operation(summary = "사용자의 하이라이트 목록 조회", description = "특정 사용자의 하이라이트 목록을 조회합니다.")
    @GetMapping("/{user_id}/highlights")
    public ApiResponse<UserResponseDTO.UserHighlightListDTO> getUserHighlights(@PathVariable Long user_id) {
        List<Highlight> userHighlights = userQueryService.getHighlightList(user_id);
        return ApiResponse.onSuccess(UserConverter.toUserHighlightListDTO(userHighlights));
    }
}
