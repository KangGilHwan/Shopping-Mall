package riverway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private OrderService orderService;

    public int getPayment(Long orderId) {
        return orderService.findById(orderId).getTotalPrice();
    }
}
