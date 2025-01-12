package UMC_7th.Closit.security.jwt;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenProvider {

    private final JwtProperties jwtProperties;

    public String createToken () {
        return null;
    }

    public boolean validToekn(){
        return false;
    }

    public Authentication getAuthentication(String token){
        return null;
    }

    private Claims getClaimsFromToken(String token){
        return null;
    }


}
