package UMC_7th.Closit.domain.user.service;

import UMC_7th.Closit.domain.user.dto.RegisterResponseDTO;
import UMC_7th.Closit.domain.user.dto.UserRequestDTO;
import UMC_7th.Closit.domain.user.entity.Role;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.domain.user.repository.UserRepository;
import UMC_7th.Closit.global.apiPayload.code.status.ErrorStatus;
import UMC_7th.Closit.global.apiPayload.exception.handler.UserHandler;
import UMC_7th.Closit.global.s3.AmazonS3Manager;
import UMC_7th.Closit.global.s3.UuidRepository;
import UMC_7th.Closit.global.s3.entity.Uuid;
import UMC_7th.Closit.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final SecurityUtil securityUtil;

    private final AmazonS3Manager amazonS3Manager;
    private final UuidRepository uuidRepository;

    @Override
    public RegisterResponseDTO registerUser (UserRequestDTO.CreateUserDTO userRequestDto) {

        // Email Already Exists
        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new UserHandler(ErrorStatus.EMAIL_ALREADY_EXISTS);
        }

        // ClositId Already Exists
        if (userRepository.existsByClositId(userRequestDto.getClositId())) {
            throw new UserHandler(ErrorStatus.CLOSITID_ALREADY_EXISTS);
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
                .clositId(user.getClositId())
                .name(userRequestDto.getName())
                .email(userRequestDto.getEmail())
                .build();

    }

    @Override
    public void deleteUser () {
        // 현재 로그인된 사용자 정보 가져오기
        User currentUser= securityUtil.getCurrentUser(); // 로그인한 사용자 (username 또는 userId 기반)

        if(currentUser == null) {
            throw new UserHandler(ErrorStatus.USER_NOT_AUTHORIZED);
        }

        userRepository.delete(currentUser);
    }

    @Override
    public User registerProfileImage (MultipartFile file) {

        // 현재 로그인된 사용자 정보
        User currentUser = securityUtil.getCurrentUser();

        // 사용자가 프로필 이미지를 삭제하려는 경우
        if (file == null || file.isEmpty()) {
            amazonS3Manager.deleteFile(currentUser.getProfileImage());
            currentUser.updateProfileImage(null);
            return currentUser;
        }

        // 기존 프로필 이미지 삭제
        if (currentUser.getProfileImage() != null) {
            amazonS3Manager.deleteFile(currentUser.getProfileImage());
        }

        // 새로운 프로필 이미지 등록
        String uuid = UUID.randomUUID().toString();
        Uuid savedUuid = uuidRepository.save(Uuid.builder().uuid(uuid).build());
        String storedLocation = amazonS3Manager.uploadFile(amazonS3Manager.generateProfileImageKeyName(savedUuid), file);

        currentUser.updateProfileImage(storedLocation);

        return currentUser;
    }

}
