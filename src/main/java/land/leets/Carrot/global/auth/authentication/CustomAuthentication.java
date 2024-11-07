package land.leets.Carrot.global.auth.authentication;

import static land.leets.Carrot.global.auth.exception.ErrorMessage.INVALID_TOKEN;
import static land.leets.Carrot.global.auth.exception.ErrorMessage.UNAUTHORIZED;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import land.leets.Carrot.global.common.response.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomAuthentication implements AuthenticationEntryPoint {

    private static final String LOG_FORMAT = "Class : {}, Code : {}, Message : {}"; // 로그 포맷 형식 정의

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        Integer exceptionCode = (Integer) request.getAttribute("jwtException");

        if (exceptionCode != null) { // JWT 예외 처리
            log.info("Authentication failed: JWT Exception with code {}", exceptionCode);
            setResponse(response, INVALID_TOKEN.getCode(), INVALID_TOKEN.getMessage());
        } else {
            // 세부적인 인증 실패 케이스 분류
            if (exceptionCode != null) { // JWT 예외 처리
                log.info("인증 실패: JWT 예외 발생 코드 {}", exceptionCode);
                setResponse(response, INVALID_TOKEN.getCode(), INVALID_TOKEN.getMessage());
            } else {
                // 세부적인 인증 실패 케이스 분류
                if (authException instanceof CredentialsExpiredException) {
                    log.error("인증 실패: 인증 자격 증명이 만료되었습니다.");
                    setResponse(response, UNAUTHORIZED.getCode(), "인증 자격 증명이 만료되었습니다. 다시 로그인 해주세요.");
                } else if (authException instanceof DisabledException) {
                    log.error("인증 실패: 계정이 비활성화되었습니다.");
                    setResponse(response, UNAUTHORIZED.getCode(), "계정이 비활성화되었습니다.");
                } else if (authException instanceof LockedException) {
                    log.error("인증 실패: 계정이 잠겼습니다.");
                    setResponse(response, UNAUTHORIZED.getCode(), "계정이 잠겼습니다.");
                } else if (authException instanceof BadCredentialsException) {
                    log.error("인증 실패: 잘못된 자격 증명입니다.");
                    setResponse(response, UNAUTHORIZED.getCode(), "잘못된 자격 증명입니다.");
                } else if (authException instanceof InsufficientAuthenticationException) {
                    log.error("인증 실패: 인증 정보가 불충분합니다. 자세한 메시지: {}",
                            authException.getMessage());
                    setResponse(response, UNAUTHORIZED.getCode(), "인증 정보가 불충분합니다.");
                } else {
                    log.error("인증 실패: 권한 없는 접근 시도.");
                    setResponse(response, UNAUTHORIZED.getCode(), "권한이 없는 접근입니다.");
                }
            }
        }
    }

    private void setResponse(HttpServletResponse response, int code, String message) throws IOException {
        response.setStatus(code); // HTTP 상태 코드를 설정
        response.setContentType("application/json"); // json 형식
        response.setCharacterEncoding("UTF-8");

        String json = new ObjectMapper().writeValueAsString(ResponseDto.errorResponse(code, message)); // 오류형태 json으로 변환
        response.getWriter().write(json); // 생성된 json 클라이언트 전송
    }
}