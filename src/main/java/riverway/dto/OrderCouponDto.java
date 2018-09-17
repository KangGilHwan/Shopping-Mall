package riverway.dto;

import riverway.domain.cart.Cart;
import riverway.domain.order.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderCouponDto {

    private Long couponId;
    private int cartId;

    public OrderCouponDto() {
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public int getCartId() {
        return cartId;
    }

    public boolean useCoupon(){
        return couponId != null;
    }

    @Override
    public String toString() {
        return "OrderCouponDto{" +
                "couponId=" + couponId +
                ", cartId=" + cartId +
                '}';
    }
}
