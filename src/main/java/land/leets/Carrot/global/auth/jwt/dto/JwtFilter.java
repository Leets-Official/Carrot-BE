package land.leets.Carrot.global.auth.jwt.dto;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = getToken(request); // 요청헤더에서 jwt 추출함

        try {
            if (token != null) { // 추출한 토큰이 null이 아니면
                Authentication authentication = jwtProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication); // 인증 정보 설정
                log.info("유효한 JWT로 인증되었습니다: {}", authentication.getName());
            }
        } catch (JwtException e) {
            handleJwtException(request, response, e); // JWT 예외 처리
            return; // 예외 발생 시 필터 체인 중단
        }
        filterChain.doFilter(request, response); //request, response 호출함
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            // "Bearer "를 뺀 부분만 반환
            return token.substring(7);
        }
        return null;
    }

    private void handleJwtException(HttpServletRequest request, HttpServletResponse response, JwtException e)
            throws IOException {
        log.warn("유효하지 않은 JWT: {}", e.getMessage());
        SecurityContextHolder.clearContext(); // SecurityContext 초기화
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 상태 코드 설정
        response.setContentType("application/json"); // 응답 타입 설정
        response.setCharacterEncoding("UTF-8");

        // 에러 응답 생성 및 전송
        String errorResponse = String.format("{\"code\": %d, \"message\": \"%s\"}",
                HttpServletResponse.SC_UNAUTHORIZED, "유효하지 않은 JWT 토큰");
        response.getWriter().write(errorResponse);
    }
}