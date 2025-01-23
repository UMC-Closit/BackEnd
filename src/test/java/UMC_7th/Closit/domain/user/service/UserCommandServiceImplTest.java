package UMC_7th.Closit.domain.user.service;

import UMC_7th.Closit.domain.user.dto.UserRequestDTO;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserCommandServiceImplTest {

    @InjectMocks
    private UserCommandService userCommandService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void registerUserSuccess () {
        // given
        UserRequestDTO.CreateUserDTO userRequestDto = new UserRequestDTO.CreateUserDTO("John", "John@email.com", "pwd12345", "john123", null, null);
        when(userRepository.existsByEmail(userRequestDto.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(userRequestDto.getPassword())).thenReturn("encodedPwd");

        // when
        userCommandService.registerUser(userRequestDto);

        // then
        // verify if userRepository.save() is called once
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void 회원가입_실패_이메일_중복 () {
        // given

        // when

        // then
    }
}