package riverway.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import riverway.Exception.UnAutorizedException;
import riverway.domain.User;
import riverway.web.HttpSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserRoleInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(UserRoleInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = HttpSessionUtils.getUserFromSession(request.getSession());
        log.debug("User Data : {}", user);
        if (user.isGuestUser()) {
            log.debug("User is Guest");
            throw new UnAutorizedException("로그인이 필요합니다.");
        }

        if (!user.isSeller()) {
            log.debug("User is not Seller");
            throw new UnAutorizedException("판매자 권한이 없습니다.");
        }

        log.debug("User is Seller");
        return true;
    }
}
