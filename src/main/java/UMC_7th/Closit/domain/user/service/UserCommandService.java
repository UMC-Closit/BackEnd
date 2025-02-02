package UMC_7th.Closit.domain.user.service;

import UMC_7th.Closit.domain.user.dto.RegisterResponseDTO;
import UMC_7th.Closit.domain.user.dto.UserRequestDTO;

public interface UserCommandService {

    RegisterResponseDTO registerUser (UserRequestDTO.CreateUserDTO userRequestDto);
}
