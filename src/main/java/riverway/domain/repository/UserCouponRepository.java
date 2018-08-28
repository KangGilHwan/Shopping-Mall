package riverway.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import riverway.domain.UserCoupon;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {
}
