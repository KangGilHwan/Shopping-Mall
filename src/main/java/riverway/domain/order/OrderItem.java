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

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    private int price;

    public OrderItem() {
    }

    public OrderItem(Product product, Option option, Coupon coupon) {
        this.product = product;
        this.option = option;
        this.coupon = coupon;
        this.price = caculatePrice();
    }

    public void belongTo(Order order) {
        this.order = order;
        order.addOrderItems(this);
    }

    public int caculatePrice() {
        return option.calculatePrice(product.getPrice());
    }

    public int getPrice() {
        return price;
    }
}
