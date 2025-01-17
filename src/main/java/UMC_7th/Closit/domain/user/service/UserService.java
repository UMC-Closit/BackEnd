package UMC_7th.Closit.domain.user.service;

import UMC_7th.Closit.domain.user.dto.LoginRequestDto;
import UMC_7th.Closit.domain.user.dto.UserDto;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public void registerUser (UserDto userDto) {

        // Email Already Exists
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        // Password Encoding
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());

        // Get Closit id

        // UserDto to User
        User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .clositId(userDto.getClositId())
                .password(encodedPassword)
                .birth(userDto.getBirth())
                .profileImage(userDto.getProfileImage())
                .build();

        userRepository.save(user);
    }
}
