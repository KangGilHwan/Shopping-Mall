package riverway.security;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import riverway.exception.UnAutorizedException;
import riverway.domain.User;
import riverway.web.HttpSessionUtils;

public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        User user = HttpSessionUtils.getUserFromSession(webRequest);
        if (!user.isGuestUser()) {
            return user;
        }

        LoginUser loginUser = parameter.getParameterAnnotation(LoginUser.class);
        if (loginUser.required()) {
            throw new UnAutorizedException("로그인이 필요합니다.");
        }
        return user;
    }
}
