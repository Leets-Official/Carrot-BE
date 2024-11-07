package land.leets.Carrot.global.auth.jwt.dto;

import static land.leets.Carrot.global.auth.exception.ErrorMessage.INVALID_TOKEN;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import land.leets.Carrot.domain.user.entity.User;
import land.leets.Carrot.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = jwtProvider.extractAccessToken(request).orElse(null);

        try {
            if (token != null && jwtProvider.isTokenValid(token)) {
                Optional<String> emailOpt = jwtProvider.extractEmail(token);
                if (emailOpt.isPresent()) {
                    String email = emailOpt.get();
                    log.info("Extracted userId from token: {}", email);

                    userRepository.findByEmail(email)
                            .ifPresentOrElse(user -> {
                                log.info("User found: {}", user.getEmail());
                                saveAuthentication(user);
                            }, () -> {
                                log.warn("User not found for userId: {}", email);
                                request.setAttribute("jwtException", INVALID_TOKEN.getCode());
                            });
                } else {
                    log.warn("email could not be extracted from token.");
                    request.setAttribute("jwtException", INVALID_TOKEN.getCode());
                }
            } else {
                log.warn("Token is invalid or not present.");
                request.setAttribute("jwtException", INVALID_TOKEN.getCode());
            }
        } catch (Exception e) {
            log.error("JWT 처리 중 예외 발생: {}", e.getMessage());
            request.setAttribute("jwtException", INVALID_TOKEN.getCode());
        }

        filterChain.doFilter(request, response);
    }

    private void saveAuthentication(User user) {
        // 인증 정보를 SecurityContext에 설정
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("유효한 JWT로 인증되었습니다: {}", user.getEmail());
        log.info("User: {}", user);
        log.info("Authorities: {}", user.getAuthorities());
        log.info("Authentication set in SecurityContext: {}", SecurityContextHolder.getContext().getAuthentication());
    }
}