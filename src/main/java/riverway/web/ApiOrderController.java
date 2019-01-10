package riverway.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import riverway.domain.User;
import riverway.domain.cart.Cart;
import riverway.domain.order.Order;
import riverway.dto.OrderDto;
import riverway.security.LoginUser;
import riverway.service.OrderService;

import javax.servlet.http.HttpSession;
import java.net.URI;

@RestController
@RequestMapping("/api/orders")
public class ApiOrderController {

    private static final Logger log = LoggerFactory.getLogger(ApiOrderController.class);

    @Autowired
    private OrderService orderService;

    @PostMapping("")
    public ResponseEntity<Void> order(@RequestBody OrderDto orderDto, @LoginUser User loginUser, HttpSession session) {
        log.debug("order : {}", orderDto);
        Cart cart = HttpSessionUtils.getCartFromSession(session);
        Order order = orderService.order(orderDto, cart, loginUser);
        session.removeAttribute(HttpSessionUtils.CART_KEY);

        URI uri = URI.create("/orders/" + order.getId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public Order show(@PathVariable Long id) {
        return orderService.findById(id);
    }
}
