package UMC_7th.Closit.domain.user.service;

import UMC_7th.Closit.domain.user.dto.RegisterResponseDto;
import UMC_7th.Closit.domain.user.dto.UserDto;

public interface UserCommandService {

    RegisterResponseDto registerUser (UserDto userDto);
}
