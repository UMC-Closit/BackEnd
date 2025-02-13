package UMC_7th.Closit.security.jwt;

import UMC_7th.Closit.global.apiPayload.code.status.ErrorStatus;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence (HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        ErrorStatus errorStatus = ErrorStatus.USER_NOT_AUTHORIZED;
//        response.setContentType("application/json");
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.getWriter().write("{\"error\": \"Unauthorized\", \"message\": \"" + authException.getMessage() + "\"}");

        response.setContentType("application/json");
        response.setStatus(errorStatus.getReasonHttpStatus().getHttpStatus().value());

        // JSON 응답에 에러 코드, 메시지, 타임스탬프 등을 포함하여 구조화된 형태로 반환
        String jsonResponse = "{" +
                "\"code\": \"" + errorStatus.getCode() + "\"," +
                "\"message\": \"" + errorStatus.getMessage() + "\"," +
                "\"timestamp\": \"" + System.currentTimeMillis() + "\"" +
                "}";
        response.getWriter().write(jsonResponse);
    }
}
