package riverway.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import riverway.domain.User;
import riverway.security.LoginUser;
import riverway.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/form")
    public String create() {
        return "/user/form";
    }

    @GetMapping("/cart")
    public String cart(@LoginUser User loginUser, Model model) {
        User user = userService.findById(loginUser.getId());
        model.addAttribute("coupons", user.getCoupons());
        log.debug("Coupon List : {}", user.getCoupons());
        return "/cart/cartList";
    }
}
