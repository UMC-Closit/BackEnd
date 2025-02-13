package UMC_7th.Closit.domain.user.service;

import UMC_7th.Closit.domain.user.dto.RegisterResponseDTO;
import UMC_7th.Closit.domain.user.dto.UserRequestDTO;
import UMC_7th.Closit.domain.user.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

public interface UserCommandService {

    RegisterResponseDTO registerUser (UserRequestDTO.CreateUserDTO userRequestDto);
    void deleteUser(Long userId);

    User registerProfileImage (MultipartFile file);
}
