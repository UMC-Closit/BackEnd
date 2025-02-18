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
import java.time.Instant;
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
        Instant now = Instant.now();
        Date issuedAt = Date.from(now);
        Date expiration = Date.from(now.plusMillis(validity));

        log.info("🔑 Creating JWT Token...");
        log.info("🕒 Issued At: {}", issuedAt);
        log.info("⏳ Expiration: {}", expiration);

        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
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
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.info("⏳ Expired Token: {}", token);
            log.info("⏳ Expired At: {}", e.getClaims().getExpiration());
            return e.getClaims(); // 만료된 토큰의 Claims 반환 (리프레시 토큰 검증 시 필요할 수 있음)
        }
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
                    .setAllowedClockSkewSeconds(120) // ✅ Clock Skew 적용 (2분 오차 허용)
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (ExpiredJwtException e) {
            log.info("⏳ Expired Token: {}", token);
            log.info("⏳ Expired At: {}", e.getClaims().getExpiration());
            log.info("⏳ Current Time: {}", new Date(System.currentTimeMillis()));
            throw new JwtHandler(ErrorStatus.EXPIRED_TOKEN);
        } catch (MalformedJwtException e) {
            log.info("🚨 Malformed Token: {}", token);
            throw new JwtHandler(ErrorStatus.INVALID_TOKEN);
        } catch (UnsupportedJwtException e) {
            log.info("🚨 Unsupported Token: {}", token);
            throw new JwtHandler(ErrorStatus.UNSUPPORTED_TOKEN);
        } catch (IllegalArgumentException e) {
            log.info("🚨 Empty Token: {}", token);
            throw new JwtHandler(ErrorStatus.EMPTY_TOKEN);
        }
    }

}


