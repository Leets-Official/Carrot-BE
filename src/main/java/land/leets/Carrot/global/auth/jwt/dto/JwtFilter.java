package land.leets.Carrot.global.auth.jwt.dto;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import land.leets.Carrot.domain.user.entity.User;
import land.leets.Carrot.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getRequestURI().equals("/api/v1/users/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        checkAccessToken(request, response, filterChain);
    }

    public void checkAccessToken(HttpServletRequest request, HttpServletResponse response,
                                 FilterChain filterChain) throws ServletException, IOException {
        log.info("checkAccessTokenAndAuthentication() 호출");
        jwtProvider.extractAccessToken(request)
                .filter(jwtProvider::isTokenValid)
                .ifPresent(accessToken -> jwtProvider.extractEmail(accessToken)
                        .ifPresent(email -> userRepository.findByEmail(email)
                                .ifPresent(this::saveAuthentication)));

        filterChain.doFilter(request, response);
    }

    private void saveAuthentication(User user) {
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.info("유효한 JWT로 인증되었습니다: {}", user.getEmail());
        log.info("Authentication set in SecurityContext: {}", SecurityContextHolder.getContext().getAuthentication());
    }
}