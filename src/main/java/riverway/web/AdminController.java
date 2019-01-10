package riverway.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import riverway.service.CouponService;
import riverway.service.UserService;

@Controller
public class AdminController {

    @Autowired
    private CouponService couponService;

    @Autowired
    private UserService userService;

    @GetMapping("/admin/coupons/form")
    public String couponForm() {
        return "/admin/couponForm";
    }

    @GetMapping("/admin/coupons")
    public String issueCoupon(Model model) {
        model.addAttribute("coupons", couponService.findAll());
        model.addAttribute("users", userService.findAll());
        return "/admin/coupons";
    }
}
