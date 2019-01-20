package riverway.domain.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import riverway.domain.User;
import riverway.dto.PaymentDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consumer_id", nullable = false)
    private User consumer;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @Embedded
    private Shipping shipping;

    @Embedded
    private OrderItems orderItems;

    private int totalPrice;

    public Order() {
    }

    public Order(User consumer, Shipping shipping, OrderItems orderItems, OrderState orderState) {
        this.consumer = consumer;
        this.shipping = shipping;
        this.orderState = orderState;
        this.orderItems = orderItems;
        this.totalPrice = orderItems.calculateTotalPrice();
    }

    public void cancel() {
        verifyNotYetShipped();
        this.orderState = OrderState.CANCEL;
    }

    private void verifyNotYetShipped() {
        if (orderState != OrderState.PAYMET_WATTING && orderState != OrderState.PREPARING) {
            throw new IllegalStateException("이미 배송 진행중입니다.");
        }
    }

    private String joinItemNames() {
        return orderItems.joinItemNames();
    }

    public Long getId() {
        return id;
    }

    public User getConsumer() {
        return consumer;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public PaymentDto toPaymentDto() {
        if (orderState != OrderState.PAYMET_WATTING) {
            throw new IllegalStateException("결제를 진행할 수 없습니다.");
        }
        return PaymentDto.of(id, consumer.getUsername(), joinItemNames(), orderItems.getNumberOfItems(), totalPrice);
    }
}
