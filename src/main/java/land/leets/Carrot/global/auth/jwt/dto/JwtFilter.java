package land.leets.Carrot.global.auth.jwt.dto;

import static land.leets.Carrot.global.auth.exception.ErrorMessage.INVALID_TOKEN;

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
                jwtProvider.validateToken(token);
                Authentication authentication = jwtProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication); // 인증 정보 설정
                log.info("유효한 JWT로 인증되었습니다: {}", authentication.getName());
            }
        } catch (JwtException e) {
            // JWT 예외 발생 시 SecurityContext 초기화
            log.info("유효하지 않은 JWT: {}", e.getMessage());
            request.setAttribute("jwtException", INVALID_TOKEN.getCode()); // 예외 정보 설정
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
}