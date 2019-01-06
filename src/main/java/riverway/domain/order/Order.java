package riverway.domain.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import riverway.domain.User;
import riverway.domain.cart.Cart;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    private int totalPrice;

    public Order() {
    }

    public Order(User consumer, Shipping shipping) {
        this.consumer = consumer;
        this.shipping = shipping;
        this.orderState = OrderState.PAYMET_WATTING;
    }

    public void addOrderItems(OrderItem orderItem){
        orderItems.add(orderItem);
    }

    public void saveTotalPrice(){
        totalPrice = calculateTotalPrice();
    }

    public int calculateTotalPrice(){
        return orderItems.stream()
                .mapToInt(OrderItem::getPrice)
                .sum();
    }

    public void cancel(){
        verifyNotYetShipped();
        this.orderState = OrderState.CANCEL;
    }

    public void verifyNotYetShipped(){
        if (!orderState.isChangeable()){
            throw new IllegalStateException("이미 배송 진행중입니다.");
        }
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

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
}
