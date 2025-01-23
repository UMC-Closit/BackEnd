package UMC_7th.Closit.domain.user.service;

import UMC_7th.Closit.domain.user.dto.JwtResponse;
import UMC_7th.Closit.domain.user.dto.LoginRequestDTO;

public interface UserAuthService {

    JwtResponse login(LoginRequestDTO loginRequestDto);
}
