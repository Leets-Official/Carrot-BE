package land.leets.Carrot.global.auth.authentication;

import static land.leets.Carrot.global.auth.exception.ErrorMessage.INVALID_TOKEN;
import static land.leets.Carrot.global.auth.exception.ErrorMessage.UNAUTHORIZED;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import land.leets.Carrot.global.common.response.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomAuthentication implements AuthenticationEntryPoint {

    private static final String LOG_FORMAT = "Class : {}, Code : {}, Message : {}"; // 로그 포맷 형식 정의

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, // 인증 실패시 호출됨
                         AuthenticationException authException) throws IOException, ServletException {
        Integer exceptionCode = (Integer) request.getAttribute(
                "jwtException"); // jwt 유효하지 않은 경우 jwtException 값 가져오기(jwt 필터)

        if (exceptionCode != null) { // JWT 예외 처리
            log.info("Authentication failed: JWT Exception with code {}", exceptionCode);
            setResponse(response, INVALID_TOKEN.getCode(), INVALID_TOKEN.getMessage());
        } else { // 그 외 인증 정보 누락 처리
            log.info("Authentication failed: Unauthorized access.");
            setResponse(response, UNAUTHORIZED.getCode(), UNAUTHORIZED.getMessage());
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
