package UMC_7th.Closit.domain.user.service;

import UMC_7th.Closit.domain.user.dto.UserDto;
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
        UserDto userDto = new UserDto("John", "John@email.com", "pwd1234", 1L, null, null);
        when(userRepository.existsByEmail(userDto.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(userDto.getPassword())).thenReturn("encodedPwd");

        // when
        userCommandService.registerUser(userDto);

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