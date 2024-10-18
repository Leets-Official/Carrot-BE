package land.leets.Carrot.global.auth.handler;

import jakarta.servlet.http.HttpServletResponse;
import land.leets.Carrot.domain.user.entity.User;
import land.leets.Carrot.global.auth.jwt.dto.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;
    
    public void handleSuccess(HttpServletResponse response, User user) {
        String token = jwtProvider.generateAccessToken(user.getEmail());
        response.setHeader("Authorization", "Bearer " + token);
        log.info("로그인 성공: 사용자 이메일 {}, 발급된 토큰: {}", user.getEmail(), token);
    }
}
