package riverway.dto;

import riverway.domain.User;
import riverway.domain.order.Order;
import riverway.domain.order.OrderItem;
import riverway.domain.order.Shipping;

import java.util.List;

public class OrderDto {

    private Shipping shipping;
    private List<OrderCouponDto> orderCoupons;

    public OrderDto() {
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public List<OrderCouponDto> getOrderCoupons() {
        return orderCoupons;
    }

    public void setOrderCoupons(List<OrderCouponDto> orderCoupons) {
        this.orderCoupons = orderCoupons;
    }

    public Order toEntity(User loginUser, List<OrderItem> orderItems) {
        return new Order(loginUser, shipping, orderItems);
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "shipping=" + shipping +
                ", orderCoupons=" + orderCoupons +
                '}';
    }
}
