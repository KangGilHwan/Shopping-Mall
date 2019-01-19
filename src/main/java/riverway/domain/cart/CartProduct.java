package riverway.domain.cart;

import riverway.domain.Coupon;
import riverway.domain.product.Product;
import riverway.domain.order.OrderItem;

import java.util.Objects;

public class CartProduct {

    private Product product;
    private Option option;

    public CartProduct(Product product, Option option) {
        this.product = product;
        this.option = option;
    }

    public void addAmount(CartProduct newItem) {
        option.addAmount(newItem.option);
    }

    public int getPrice() {
        return option.calculatePrice(product.getPrice());
    }

    public Product getProduct() {
        return product;
    }

    public Option getOption() {
        return option;
    }

    public OrderItem toOrderItem(Coupon coupon) {
        return new OrderItem(product, option, coupon);
    }

    public OrderItem toOrderItem() {
        return new OrderItem(product, option, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartProduct product1 = (CartProduct) o;
        return Objects.equals(product, product1.product) &&
                Objects.equals(option, product1.option);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, option);
    }

    @Override
    public String toString() {
        return "CartProduct{" +
                "product=" + product +
                ", option=" + option +
                '}';
    }
}
