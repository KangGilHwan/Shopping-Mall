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

    @Embedded
    private Shipping shipping;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order() {
    }

    public Order(User consumer, Shipping shipping) {
        this.consumer = consumer;
        this.shipping = shipping;
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
