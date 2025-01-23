package UMC_7th.Closit.domain.user.controller;

import UMC_7th.Closit.domain.user.dto.JwtResponse;
import UMC_7th.Closit.domain.user.dto.LoginRequestDTO;
import UMC_7th.Closit.domain.user.dto.RegisterResponseDTO;
import UMC_7th.Closit.domain.user.dto.UserRequestDTO;
import UMC_7th.Closit.domain.user.service.UserAuthService;
import UMC_7th.Closit.domain.user.service.UserCommandService;
import UMC_7th.Closit.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<JwtResponse> login (@RequestBody @Valid LoginRequestDTO loginRequestDto) {
        JwtResponse jwtResponse = userAuthService.login(loginRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(jwtResponse);
    }

}
