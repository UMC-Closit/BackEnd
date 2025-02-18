package UMC_7th.Closit.security.jwt;

import UMC_7th.Closit.domain.user.entity.Role;
import UMC_7th.Closit.domain.user.service.CustomUserDetailService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailService customUserDetailsService; // 🔹 UserDetailsService 추가

    @Override
    protected void doFilterInternal (
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String token = resolveToken(request);
        Cookie[] cookies = request.getCookies();
        String header = request.getHeader("Authorization");
        HttpSession session = request.getSession();

        log.info("🔍 [JwtAuthenticationFilter] - Incoming Request: {}", request.getRequestURI());
        log.info("🔍 [JwtAuthenticationFilter] - Authorization Header: {}", header);
        log.info("🔍 [JwtAuthenticationFilter] - Session: {}", session);
        log.info("🔍 [JwtAuthenticationFilter] - Cookies: {}", (cookies != null ? Arrays.toString(cookies) : "No Cookies Found"));
        log.info("🔍 [JwtAuthenticationFilter] - Extracted Token: {}", token);


        if (token != null && jwtTokenProvider.validateToken(token)) {
            Claims claims = jwtTokenProvider.getClaims(token);
            String email = claims.getSubject();
            String roleString = claims.get("role", String.class);
            Date issuedAt = claims.getIssuedAt();
            Date expiration = claims.getExpiration();

            log.info("✅ [JwtAuthenticationFilter] - Token Claims: {}", claims);
            log.info("✅ [JwtAuthenticationFilter] - Extracted Email: {}", email);
            log.info("✅ [JwtAuthenticationFilter] - Extracted Role: {}", roleString);
            log.info("✅ [JwtAuthenticationFilter] - Issued At: {}", issuedAt);
            log.info("✅ [JwtAuthenticationFilter] - Expiration: {}", expiration);

            Role role = Role.valueOf(roleString); // String->Role 반환
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

            // SecurityContext에 UserDetails 설정
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, // principal을 UserDetails 객체로 설정
                    null,
                    List.of(new SimpleGrantedAuthority("ROLE_"+role.name())) // authorities를 SimpleGrantedAuthority 객체로 설정
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken (HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
