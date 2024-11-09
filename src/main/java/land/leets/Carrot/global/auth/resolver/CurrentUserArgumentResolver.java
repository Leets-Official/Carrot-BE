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
        boolean hasAnnotation = parameter.hasParameterAnnotation(CurrentUser.class);
        boolean parameterType = Long.class.isAssignableFrom(parameter.getParameterType());
        return hasAnnotation && parameterType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new IllegalArgumentException("익명 사용자로부터의 요청은 지원되지 않습니다.");
        }

        String token = Optional.ofNullable(webRequest.getHeader("Authorization"))
                .map(accessToken -> accessToken.replace("Bearer ", "")).get();

        return jwtProvider.extractId(token).get();
    }
}
