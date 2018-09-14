package riverway.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderCouponDto {

    private Long couponId;
    private Long cartId;

    public OrderCouponDto() {
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public Long getCartId() {
        return cartId;
    }

    @Override
    public String toString() {
        return "OrderCouponDto{" +
                "couponId=" + couponId +
                ", cartId=" + cartId +
                '}';
    }
}
