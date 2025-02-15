package UMC_7th.Closit.domain.follow.controller;

import UMC_7th.Closit.domain.follow.converter.FollowConverter;
import UMC_7th.Closit.domain.follow.dto.FollowRequestDTO;
import UMC_7th.Closit.domain.follow.dto.FollowResponseDTO;
import UMC_7th.Closit.domain.follow.entity.Follow;
import UMC_7th.Closit.domain.follow.service.FollowCommandService;
import UMC_7th.Closit.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/follows")
public class FollowController {

    private final FollowCommandService followCommandService;

    @Operation(summary = "팔로우 생성", description = "새로운 팔로우를 생성합니다.")
    @PostMapping
    public ApiResponse<FollowResponseDTO.CreateFollowResultDTO> createFollow(@RequestBody @Valid FollowRequestDTO.CreateFollowDTO request) {
        Follow follow = followCommandService.createFollow(request);
        return ApiResponse.onSuccess(FollowConverter.toCreateFollowResultDTO(follow));
    }

    @Operation(summary = "팔로우 여부 조회", description = "특정 유저를 팔로우하는지 여부를 조회합니다.")
    @GetMapping("/{follower_closit_id}/{following_closit_id}")
    public ApiResponse<Boolean> checkFollow(@PathVariable String follower_closit_id) {
        return ApiResponse.onSuccess(followCommandService.isFollowing(follower_closit_id));
    }

    @Operation(summary = "팔로우 삭제", description = "특정 팔로우를 삭제합니다.")
    @DeleteMapping("/{follower_closit_id}/{following_closit_id}")
    public ApiResponse<String> deleteFollow(
            @PathVariable String follower_closit_id,
            @PathVariable String following_closit_id ) {
        followCommandService.deleteFollow(follower_closit_id, following_closit_id);
        return ApiResponse.onSuccess("Deleted Follow with follower closit ID: " + follower_closit_id + ", following closit ID: " + following_closit_id);
    }
}
