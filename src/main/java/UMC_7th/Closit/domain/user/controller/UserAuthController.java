package UMC_7th.Closit.domain.user.controller;

import UMC_7th.Closit.domain.user.dto.JwtResponse;
import UMC_7th.Closit.domain.user.dto.LoginRequestDto;
import UMC_7th.Closit.domain.user.dto.UserDto;
import UMC_7th.Closit.domain.user.service.UserAuthService;
import UMC_7th.Closit.domain.user.service.UserService;
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

    private final UserService userService;
    private final UserAuthService userAuthService;

    @PostMapping("/register")
    public ResponseEntity<String> register (@RequestBody @Valid UserDto userDto){
        userService.registerUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입이 완료되었습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login (@RequestBody @Valid LoginRequestDto loginRequestDto) {
        JwtResponse jwtResponse = userAuthService.login(loginRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(jwtResponse);
    }
}
