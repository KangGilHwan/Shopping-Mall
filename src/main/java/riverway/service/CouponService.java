package riverway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import riverway.domain.Coupon;
import riverway.domain.User;
import riverway.domain.UserCoupon;
import riverway.domain.repository.CouponRepository;
import riverway.domain.repository.UserCouponRepository;
import riverway.domain.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CouponService {

    @Autowired
    private UserService userService;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private UserCouponRepository userCouponRepository;

    public Coupon save(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    public Coupon findById(Long id) {
        return couponRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Coupon> findAll() {
        return couponRepository.findAll();
    }

    @Transactional
    public UserCoupon save(Long userId, Long couponId){
        User user = userService.findById(userId);
        Coupon coupon = findById(couponId);
        UserCoupon userCoupon = new UserCoupon(user, coupon);
        return userCouponRepository.save(userCoupon);
    }

    @Transactional
    public List<UserCoupon> issueCouponAllUsers(Long couponId) {
        Coupon coupon = findById(couponId);
        List<UserCoupon> userCoupons = new ArrayList<>();

        List<User> users = userService.findAllUsers();
        for (User user : users){
            UserCoupon userCoupon = new UserCoupon(user, coupon);
            userCoupons.add(userCoupon);
        }
        return userCouponRepository.saveAll(userCoupons);
    }
}
