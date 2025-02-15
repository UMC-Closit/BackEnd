package UMC_7th.Closit.domain.user.controller;

import UMC_7th.Closit.domain.highlight.entity.Highlight;
import UMC_7th.Closit.domain.mission.entity.Mission;
import UMC_7th.Closit.domain.user.converter.UserConverter;
import UMC_7th.Closit.domain.user.dto.UserResponseDTO;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.domain.user.service.UserCommandService;
import UMC_7th.Closit.domain.user.service.UserQueryService;
import UMC_7th.Closit.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/users")
@Slf4j
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    @Operation(summary = "사용자 삭제", description = "특정 사용자를 삭제합니다.")
    @DeleteMapping("/")
    public ApiResponse<String> deleteUser() {

        userCommandService.deleteUser();
        return ApiResponse.onSuccess("Deletion successful");
    }

    @Operation(summary = "사용자 프로필 이미지 등록", description = "특정 사용자의 프로필 이미지를 등록합니다.")
    @PostMapping(
            value = "/{closit_id}/profile-image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    public ApiResponse<UserResponseDTO.UserInfoDTO> registerProfileImage(@RequestPart(value = "user_image", required = false) MultipartFile profileImage) {
        User userInfo = userCommandService.registerProfileImage(profileImage);
        return ApiResponse.onSuccess(UserConverter.toUserInfoDTO(userInfo));
    }

    @Operation(summary = "사용자 정보 조회", description = "특정 사용자의 정보를 조회합니다.")
    @GetMapping("/{closit_id}")
    public ApiResponse<UserResponseDTO.UserInfoDTO> getUserInfo(@PathVariable String closit_id) {
        User userInfo = userQueryService.getUserInfo(closit_id);
        return ApiResponse.onSuccess(UserConverter.toUserInfoDTO(userInfo));
    }

    @Operation(summary = "사용자의 팔로워 목록 조회", description = "특정 사용자의 팔로워 목록을 조회합니다.")
    @GetMapping("/{closit_id}/followers")
    public ApiResponse<UserResponseDTO.UserFollowerSliceDTO> getUserFollowers(
            @PathVariable String closit_id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Slice<User> followerSlice = userQueryService.getFollowerList(closit_id, PageRequest.of(page, size));
        return ApiResponse.onSuccess(UserConverter.toUserFollowerSliceDTO(followerSlice));
    }

    @Operation(summary = "사용자의 팔로잉 목록 조회", description = "특정 사용자의 팔로잉 목록을 조회합니다.")
    @GetMapping("/{closit_id}/following")
    public ApiResponse<UserResponseDTO.UserFollowingSliceDTO> getUserFollowing(
            @PathVariable String closit_id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Slice<User> followingSlice = userQueryService.getFollowingList(closit_id, PageRequest.of(page, size));
        return ApiResponse.onSuccess(UserConverter.toUserFollowingSliceDTO(followingSlice));
    }

    @Operation(summary = "사용자의 하이라이트 목록 조회", description = "특정 사용자의 하이라이트 목록을 조회합니다.")
    @GetMapping("/{closit_id}/highlights")
    public ApiResponse<UserResponseDTO.UserHighlightSliceDTO> getUserHighlights(
            @PathVariable String closit_id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Slice<Highlight> highlightSlice = userQueryService.getHighlightList(closit_id, PageRequest.of(page, size));

        return ApiResponse.onSuccess(UserConverter.toUserHighlightSliceDTO(highlightSlice));
    }

    @Operation(summary = "사용자의 미션 목록 조회", description = "특정 사용자의 미션 목록을 조회합니다.")
    @GetMapping("/{closit_id}/missions")
    public ApiResponse<UserResponseDTO.UserMissionSliceDTO> getUserMissions(
            @PathVariable String closit_id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Slice<Mission> missionSlice = userQueryService.getMissionList(closit_id, PageRequest.of(page, size));

        return ApiResponse.onSuccess(UserConverter.toUserMissionSliceDTO(missionSlice));
    }

    @Operation(summary = "closit id 중복 여부 조회", description = "특정 closit id가 이미 있는 id인지 조회합니다.")
    @GetMapping("/isunique/{closit_id}")
    public ApiResponse<Boolean> getUserMissions(@PathVariable String closit_id) {
        return ApiResponse.onSuccess(userCommandService.isClositIdUnique(closit_id));
    }
}
