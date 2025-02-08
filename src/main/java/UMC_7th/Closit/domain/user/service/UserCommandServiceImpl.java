package UMC_7th.Closit.domain.user.service;

import UMC_7th.Closit.domain.user.dto.RegisterResponseDTO;
import UMC_7th.Closit.domain.user.dto.UserRequestDTO;
import UMC_7th.Closit.domain.user.entity.Role;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.domain.user.repository.UserRepository;
import UMC_7th.Closit.global.apiPayload.code.status.ErrorStatus;
import UMC_7th.Closit.global.apiPayload.exception.handler.UserHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.amazonaws.services.ec2.model.PrincipalType.Role;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponseDTO registerUser (UserRequestDTO.CreateUserDTO userRequestDto) {

        // Email Already Exists
        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new UserHandler(ErrorStatus.EMAIL_ALREADY_EXISTS);
        }

        // Password Encoding
        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());


        // UserDto to User
        User user = User.builder()
                .name(userRequestDto.getName())
                .email(userRequestDto.getEmail())
                .clositId(userRequestDto.getClositId())
                .password(encodedPassword)
                .birth(userRequestDto.getBirth())
                .profileImage(userRequestDto.getProfileImage())
                .role(UMC_7th.Closit.domain.user.entity.Role.USER) // 기본적으로 USER 부여
                .build();

        userRepository.save(user);

        return RegisterResponseDTO.builder()
                .name(userRequestDto.getName())
                .email(userRequestDto.getEmail())
                .build();

    }

    @Override
    public void deleteUser (Authentication authentication, Long userId) {
        // 현재 로그인된 사용자 정보 가져오기
        String currentUsername = authentication.getName(); // 로그인한 사용자 (username 또는 userId 기반)
        User currentUser = userRepository.findByName(currentUsername)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        User targetUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        // 자기 자신이거나 관리자 권한이 있는 경우만 삭제 가능
        if (!currentUser.getId().equals(userId) && !currentUser.getRole().equals(UMC_7th.Closit.domain.user.entity.Role.USER)) {
            throw new UserHandler(ErrorStatus.USER_NOT_AUTHORIZED);
        }

        log.info("사용자 삭제 진행: userId={}, 삭제자={}", userId, currentUsername);
        userRepository.delete(targetUser);
    }


}
