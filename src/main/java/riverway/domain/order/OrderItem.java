package riverway.domain.order;

import riverway.domain.Coupon;
import riverway.domain.Product;
import riverway.domain.cart.Option;

import javax.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Embedded
    private Option option;

    @OneToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    public OrderItem() {
    }

    public OrderItem(Product product, Option option, Coupon coupon) {
        this.product = product;
        this.option = option;
        this.coupon = coupon;
    }
}
