package riverway.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import riverway.domain.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
