package riverway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import riverway.domain.Coupon;
import riverway.domain.User;
import riverway.domain.UserCoupon;
import riverway.domain.cart.Cart;
import riverway.domain.order.Order;
import riverway.domain.order.OrderItem;
import riverway.domain.repository.OrderItemRepository;
import riverway.domain.repository.OrderRepository;
import riverway.domain.repository.UserCouponRepository;
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

    @Autowired
    private UserCouponRepository userCouponRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Transactional
    public Order order(OrderDto orderDto, Cart cart, User loginUser) {
        OrderItem orderItem;
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderCouponDto orderCoupon : orderDto.getOrderCoupons()) {
            orderItem = cartToOrderItem(orderCoupon, cart, loginUser);
            orderItems.add(orderItem);
        }
        Order order = save(orderDto.toOrder(loginUser, orderItems));
        return order;
    }

    private OrderItem cartToOrderItem(OrderCouponDto orderCoupon, Cart cart, User user) {
        if (!orderCoupon.useCoupon()) {
            return cart.toOrderItem(orderCoupon.getCartId());
        }
        Coupon coupon = couponService.findById(orderCoupon.getCouponId());
        UserCoupon userCoupon = findCouponOfUser(coupon, user);
        userCoupon.use();
        return cart.toOrderItem(coupon, orderCoupon.getCartId());
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public UserCoupon findCouponOfUser(Coupon coupon, User user){
        return userCouponRepository.findByCouponAndUser(coupon, user).orElseThrow(EntityNotFoundException::new);
    }
}
