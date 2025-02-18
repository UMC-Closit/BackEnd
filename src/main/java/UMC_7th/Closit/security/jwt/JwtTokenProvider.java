package UMC_7th.Closit.security.jwt;

import UMC_7th.Closit.domain.user.entity.Role;
import UMC_7th.Closit.global.apiPayload.code.status.ErrorStatus;
import UMC_7th.Closit.global.apiPayload.exception.handler.JwtHandler;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {


    private final Key key;
    private final Long accessTokenValidity;
    private final Long refreshTokenValidity;

    public JwtTokenProvider(@Value("${jwt.secret-key}") String secretKey,
                            @Value("${jwt.expiration.access}") String accessTokenValidity,
                            @Value("${jwt.expiration.refresh}") String refreshTokenValidity) {
        String base64Secret = Base64.getEncoder().encodeToString(secretKey.getBytes());
        this.key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(base64Secret));
        this.accessTokenValidity = Long.parseLong(accessTokenValidity);
        this.refreshTokenValidity = Long.parseLong(refreshTokenValidity);
    }

    public String createToken(String email, Role role, long validity) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(Date.from(ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toInstant()))
                .setExpiration(Date.from(ZonedDateTime.now(ZoneId.of("Asia/Seoul"))
                        .plus(Duration.ofMillis(validity))
                        .toInstant()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String createAccessToken(String email, Role role) {
        return createToken(email, role, accessTokenValidity);
    }

    public String createRefreshToken(String email, Role role) {
        return createToken(email, role, refreshTokenValidity);
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {
        try {
            log.info("🔍 Validating Token: {}", token);
            Claims claims = getClaims(token);
            log.info("🔍 Token Claims: {}", claims);
            log.info("🔍 Token Subject: {}", claims.getSubject());
            log.info("🔍 Token Expiration: {}", claims.getExpiration());
            log.info("🔍 Token Issued At: {}", claims.getIssuedAt());

            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.info("🔍 Expired Token: {}", token);
            log.info("🔍 Expired At: {}", e.getClaims().getExpiration());
            log.info("🔍 Expired At System: {}", new Date(System.currentTimeMillis()));
            log.info("🔍 Expired At Instant: {}", Instant.ofEpochMilli(e.getClaims().getExpiration().getTime()));
            throw new JwtHandler(ErrorStatus.EXPIRED_TOKEN);
        } catch (MalformedJwtException e) {
            log.info("🔍 Malformed Token: {}", token);
            throw new JwtHandler(ErrorStatus.INVALID_TOKEN);
        } catch (UnsupportedJwtException e) {
            log.info("🔍 Unsupported Token: {}", token);
            throw new JwtHandler(ErrorStatus.UNSUPPORTED_TOKEN);
        } catch (IllegalArgumentException e) {
            log.info("🔍 Empty Token: {}", token);
            throw new JwtHandler(ErrorStatus.EMPTY_TOKEN);
        }
    }

    public String resolveAccessToken(String bearerToken) {
        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            throw new JwtHandler(ErrorStatus.INVALID_TOKEN);
        }

        String token = bearerToken.substring(7);
        return bearerToken.substring(7).trim();
    }

    public String getEmail(String token) {
        return getClaims(token).getSubject();
    }

}


