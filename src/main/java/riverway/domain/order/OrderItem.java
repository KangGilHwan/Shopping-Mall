package riverway.domain.order;

import riverway.domain.Coupon;
import riverway.domain.product.Product;
import riverway.domain.cart.Option;

import javax.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
        this.price = calculatePrice();
    }

    public int caculatePriceNoCoupon() {
        return option.calculatePrice(product.getPrice());
    }

    public int calculatePrice() {
        if (coupon == null) {
            return caculatePriceNoCoupon();
        }
        return coupon.discount(caculatePriceNoCoupon());
    }

    public int getPrice() {
        return price;
    }

    public String getItemName(){
        return product.getName();
    }
}
