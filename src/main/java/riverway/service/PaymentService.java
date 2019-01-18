package riverway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riverway.dto.PaymentDto;

@Service
public class PaymentService {

    @Autowired
    private OrderService orderService;

    public PaymentDto getPayment(Long orderId) {
        return orderService.findById(orderId).toPaymentDto();
    }
}
