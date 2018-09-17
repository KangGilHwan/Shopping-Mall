package riverway.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import riverway.domain.Coupon;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
