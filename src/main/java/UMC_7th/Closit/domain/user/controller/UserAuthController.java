package UMC_7th.Closit.domain.user.controller;

import UMC_7th.Closit.domain.user.dto.*;
import UMC_7th.Closit.domain.user.entity.Role;
import UMC_7th.Closit.domain.user.service.UserAuthService;
import UMC_7th.Closit.domain.user.service.UserCommandService;
import UMC_7th.Closit.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserCommandService userCommandService;
    private final UserAuthService userAuthService;

    @PostMapping("/register")
    public ApiResponse<RegisterResponseDTO> register (@RequestBody @Valid UserRequestDTO.CreateUserDTO userRequestDto){
        RegisterResponseDTO responseDto = userCommandService.registerUser(userRequestDto);

        return ApiResponse.onSuccess(responseDto);
    }

    @PostMapping("/login")
    public ApiResponse<JwtResponse> login (@RequestBody @Valid LoginRequestDTO loginRequestDto) {
        JwtResponse jwtResponse = userAuthService.login(loginRequestDto);

        return ApiResponse.onSuccess(jwtResponse);
    }

    @PatchMapping("/{user_id}/role")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<UserResponseDTO.UserInfoDTO> changeRole(@PathVariable Long user_id, @RequestParam Role newRole) {
        UserResponseDTO.UserInfoDTO userInfoDTO = userAuthService.updateUserRole(user_id, newRole);

        return ApiResponse.onSuccess(userInfoDTO);
    }
}
