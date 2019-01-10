package riverway.security;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import riverway.domain.User;
import riverway.exception.UnAuthenticationException;
import riverway.service.UserService;
import riverway.web.HttpSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;

public class BasicAuthInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(BasicAuthInterceptor.class);

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        log.debug("Authorization : {}", authorization);
        if (authorization == null || !authorization.startsWith("Basic")) {
            return true;
        }
        String basic64Credentials = authorization.substring("Basic".length()).trim();
        String credentials = new String(Base64.decode(basic64Credentials), Charset.forName("UTF-8"));
        final String[] values = credentials.split(":", 2);
        log.debug("username : {}", values[0]);
        log.debug("password : {}", values[1]);

        try {
            User user = userService.login(values[0], values[1]);
            log.debug("Login success : {}", user);
            request.getSession().setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
            return true;
        } catch (UnAuthenticationException e) {
            log.debug("Fail to login : {}", e.getMessage());
            return true;
        }
    }
}
