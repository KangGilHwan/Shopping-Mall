package riverway.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import riverway.domain.Coupon;
import riverway.domain.UserCoupon;
import riverway.service.CouponService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/coupons")
public class ApiCouponController {

    private static final Logger log = LoggerFactory.getLogger(ApiCouponController.class);

    @Autowired
    private CouponService couponService;

    @PostMapping("")
    public ResponseEntity<Void> create(@RequestBody Coupon coupon){
        Coupon savedCoupon = couponService.save(coupon);
        URI uri = URI.create("/api/coupons/" + savedCoupon.getId());
        log.debug("new Coupon : {}", savedCoupon);
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public Coupon show(@PathVariable Long id){
        return couponService.findById(id);
    }

    @PostMapping("/{couponId}/users/{userId}")
    public ResponseEntity<Void> issueCoupon(@PathVariable Long couponId, @PathVariable Long userId){
        UserCoupon userCoupon = couponService.save(userId, couponId);
        log.debug("유저에게 쿠폰 발급 : {}", userCoupon);
        URI uri = URI.create(String.format("/api/coupons/%d/users/%d", couponId, userId));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{couponId}/users")
    public ResponseEntity<Void> issueCouponAllUsers(@PathVariable Long couponId){
        List<UserCoupon> userCoupons = couponService.issueCouponAllUsers(couponId);
        log.debug("모든 유저에게 쿠폰 발급 : {}", userCoupons);
        return ResponseEntity.ok().build();
    }
}
