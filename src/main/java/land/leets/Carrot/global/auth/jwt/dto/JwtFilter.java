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
                jwtProvider.validateToken(token); // 유효성 검증
                Authentication authentication = jwtProvider.getAuthentication(token); //authentication 객체 가져오기
                SecurityContextHolder.getContext().setAuthentication(authentication); // SecurityContextHolder에 설정함
            }
        } catch (JwtException e) { // 유효성 검증 실패시 JwtException 발생
            log.info("error token: {}", e.getMessage()); // 로그 메세지 기록
            request.setAttribute("jwtException", INVALID_TOKEN.getCode()); // 유효하지 않은 토큰 401
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