package riverway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import riverway.domain.Coupon;
import riverway.domain.User;
import riverway.domain.cart.Cart;
import riverway.domain.order.Order;
import riverway.domain.order.OrderItem;
import riverway.domain.order.OrderItems;
import riverway.domain.repository.OrderRepository;
import riverway.dto.OrderCouponDto;
import riverway.dto.OrderDto;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private CouponService couponService;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Order order(OrderDto orderDto, Cart cart, User loginUser) {
        OrderItem orderItem;
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderCouponDto orderCoupon : orderDto.getOrderCoupons()) {
            orderItem = cartToOrderItem(orderCoupon, cart, loginUser);
            orderItems.add(orderItem);
        }
        Order order = orderDto.toEntity(loginUser, new OrderItems(orderItems));
        return orderRepository.save(order);
    }

    private OrderItem cartToOrderItem(OrderCouponDto orderCoupon, Cart cart, User user) {
        if (orderCoupon.isCouponEmpty()) {
            return orderCoupon.toOrderItem(cart);
        }
        Coupon coupon = couponService.use(user, orderCoupon.getCouponId());
        return orderCoupon.toOrderItemWithCoupon(cart, coupon);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
