package riverway.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import riverway.domain.Coupon;
import riverway.service.CouponService;

import java.net.URI;

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
}
