package riverway.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CouponTest {

    @Test
    public void 쿠폰할인() {
        Coupon coupon = new Coupon("첫 구매 할인", 5000);
        assertThat(coupon.discount(12000)).isEqualTo(7000);
    }

    @Test(expected = IllegalStateException.class)
    public void 쿠폰할인_최소금액이하() {
        Coupon coupon = new Coupon("추석 할인 쿠폰", 3000);
        coupon.discount(6000);
    }
}