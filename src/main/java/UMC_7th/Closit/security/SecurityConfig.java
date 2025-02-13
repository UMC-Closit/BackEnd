package UMC_7th.Closit.security;

import UMC_7th.Closit.domain.user.service.CustomUserDetailService;
import UMC_7th.Closit.security.jwt.JwtAccessDeniedHandler;
import UMC_7th.Closit.security.jwt.JwtAuthenticationEntryPoint;
import UMC_7th.Closit.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import UMC_7th.Closit.security.jwt.JwtAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
//                .exceptionHandling((exceptionHandling) -> exceptionHandling
//                        .accessDeniedHandler(jwtAccessDeniedHandler)
//                        .authenticationEntryPoint(jwtAuthenticationEntryPoint))
//                // disable session management
//                .sessionManagement((sessionManagement) -> sessionManagement
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 폼 기반 로그인 설정
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true))
                // 경로 접속 권한 설정
                .authorizeHttpRequests((authorizeRequests) -> authorizeRequests
                        .requestMatchers("/api/auth/**", "/api/public/**").permitAll()
                        .requestMatchers("/","/swagger-ui/**","/v3/api-docs/**").permitAll()
                        .requestMatchers("/user").hasRole("USER")
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
