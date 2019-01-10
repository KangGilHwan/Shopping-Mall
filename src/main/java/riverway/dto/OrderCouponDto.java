package riverway.dto;

import riverway.domain.Coupon;
import riverway.domain.cart.Cart;
import riverway.domain.order.OrderItem;

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

    public boolean isCouponEmpty() {
        return couponId == null;
    }

    public OrderItem toOrderItem(Cart cart) {
        return cart.toOrderItem(cartId);
    }

    public OrderItem toOrderItemWithCoupon(Cart cart, Coupon coupon) {
        return cart.toOrderItem(cartId, coupon);
    }

    @Override
    public String toString() {
        return "OrderCouponDto{" +
                "couponId=" + couponId +
                ", cartId=" + cartId +
                '}';
    }
}
