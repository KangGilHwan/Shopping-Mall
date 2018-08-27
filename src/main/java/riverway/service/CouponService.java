package riverway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riverway.domain.Coupon;
import riverway.domain.repository.CouponRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

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
}
