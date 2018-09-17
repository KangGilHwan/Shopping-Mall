package riverway.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import riverway.domain.Coupon;
import riverway.domain.User;
import riverway.domain.UserCoupon;

import java.util.Optional;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {
    Optional<UserCoupon> findByCouponAndUser(Coupon coupon, User user);
}
