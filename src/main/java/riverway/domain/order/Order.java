package riverway.domain.order;

import riverway.domain.User;
import riverway.domain.cart.Cart;

import javax.persistence.*;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private User consumer;

    @Embedded
    private Shipping shipping;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    public Order() {
    }

    public Order(User consumer, Shipping shipping, List<OrderItem> orderItems) {
        this.consumer = consumer;
        this.shipping = shipping;
        this.orderItems = orderItems;
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
