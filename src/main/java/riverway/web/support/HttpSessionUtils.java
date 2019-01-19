package riverway.web.support;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import riverway.domain.User;
import riverway.domain.cart.Cart;

import javax.servlet.http.HttpSession;

public class HttpSessionUtils {
    public static final String USER_SESSION_KEY = "loginedUser";
    public static final String CART_KEY = "cart";

    public static boolean isLoginUser(NativeWebRequest webRequest) {
        Object loginedUser = webRequest.getAttribute(USER_SESSION_KEY, WebRequest.SCOPE_SESSION);
        return loginedUser != null;
    }

    public static User getUserFromSession(NativeWebRequest webRequest) {
        if (!isLoginUser(webRequest)) {
            return User.GUEST_USER;
        }
        return (User) webRequest.getAttribute(USER_SESSION_KEY, WebRequest.SCOPE_SESSION);
    }

    public static boolean isLoginUser(HttpSession httpSession) {
        Object sessionedUser = httpSession.getAttribute(USER_SESSION_KEY);
        if (sessionedUser == null) {
            return false;
        }
        return true;
    }

    public static User getUserFromSession(HttpSession session) {
        if (!isLoginUser(session)) {
            return User.GUEST_USER;
        }
        return (User) session.getAttribute(USER_SESSION_KEY);
    }

    public static boolean hasCart(HttpSession session) {
        Object sessionedCart = session.getAttribute(CART_KEY);
        if (sessionedCart == null) {
            return false;
        }
        return true;
    }

    public static Cart getCartFromSession(HttpSession session) {
        if (!hasCart(session)) {
            Cart cart = new Cart();
            session.setAttribute(CART_KEY, cart);
            return cart;
        }
        return (Cart) session.getAttribute(CART_KEY);
    }
}
