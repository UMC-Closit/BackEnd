package UMC_7th.Closit.domain.user.service;

import UMC_7th.Closit.domain.user.converter.UserConverter;
import UMC_7th.Closit.domain.user.dto.JwtResponse;
import UMC_7th.Closit.domain.user.dto.LoginRequestDTO;
import UMC_7th.Closit.domain.user.dto.UserResponseDTO;
import UMC_7th.Closit.domain.user.entity.RefreshToken;
import UMC_7th.Closit.domain.user.entity.Role;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.domain.user.repository.RefreshTokenRepository;
import UMC_7th.Closit.domain.user.repository.UserRepository;
import UMC_7th.Closit.global.apiPayload.code.status.ErrorStatus;
import UMC_7th.Closit.global.apiPayload.exception.GeneralException;
import UMC_7th.Closit.global.apiPayload.exception.handler.UserHandler;
import UMC_7th.Closit.security.jwt.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserAuthServiceImpl implements UserAuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;

    // login
    @Override
    public JwtResponse login(LoginRequestDTO loginRequestDto) {
        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new GeneralException(ErrorStatus.PASSWORD_NOT_CORRESPOND);
        }


        String accessToken = jwtTokenProvider.createAccessToken(user.getEmail(), user.getRole());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getEmail(), user.getRole());

        return new JwtResponse(accessToken, refreshToken);
    }

    @Override
    public UserResponseDTO.UserInfoDTO updateUserRole (Long userId, Role newRole) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        User updatedUser = user.updateRole(newRole);

        return UserConverter.toUserInfoDTO(updatedUser);
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        // Refresh Token 유효성 검사
        jwtTokenProvider.validateToken(refreshToken);

        // Refresh Token에서 email 추출
        String resolvedToken = jwtTokenProvider.resolveToken(refreshToken);
        String email = jwtTokenProvider.getEmail(resolvedToken);

        // 서버에 저장된 refresh token과 비교
        RefreshToken savedToken = refreshTokenRepository.findByUsername(email)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        if (!savedToken.getRefreshToken().equals(refreshToken)) {
            throw new GeneralException(ErrorStatus.INVALID_REFRESH_TOKEN);
        }
        log.info("username: {}", email);

        // Token에서 Role 추출
        Claims claims = jwtTokenProvider.getClaims(resolvedToken);
        Role role = claims.get("role", Role.class);

        // 새로운 Access Token, Refresh Token 생성
        String newAccessToken = jwtTokenProvider.createAccessToken(email, role);
        String newRefreshToken = jwtTokenProvider.createRefreshToken(email, role);

        // Refresh Token 업데이트
        savedToken.updateRefreshToken(newRefreshToken);
        refreshTokenRepository.save(new RefreshToken(email, newRefreshToken));

        return new JwtResponse(newAccessToken, newRefreshToken);
    }
}
