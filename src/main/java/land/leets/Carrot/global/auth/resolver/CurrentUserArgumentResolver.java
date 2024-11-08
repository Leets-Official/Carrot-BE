package land.leets.Carrot.global.auth.resolver;

import java.util.Optional;
import land.leets.Carrot.global.auth.annotation.CurrentUser;
import land.leets.Carrot.global.auth.jwt.dto.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {
    private final JwtProvider jwtProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new IllegalArgumentException("익명 사용자로부터의 요청은 지원되지 않습니다.");
        }

        String token = Optional.ofNullable(webRequest.getHeader("Authorization"))
                .map(accessToken -> accessToken.replace("Bearer ", ""))
                .orElseThrow(() -> new IllegalArgumentException("Authorization 헤더가 누락되었습니다."));

        return jwtProvider.extractId(token)
                .orElseThrow(() -> new IllegalStateException("유효하지 않은 토큰이거나 토큰에 ID 정보가 없습니다."));
    }
}
