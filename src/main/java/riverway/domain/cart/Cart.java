package riverway.domain.cart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import riverway.domain.Coupon;
import riverway.domain.order.OrderItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Cart {

    private static final Logger log = LoggerFactory.getLogger(Cart.class);

    private List<CartProduct> cartProducts = new ArrayList<>();

    public Cart() {
    }

    public void addCart(CartProduct newItem) {
        Optional<CartProduct> maybeItem = findSameItem(newItem);
        if (maybeItem.isPresent()) {
            log.debug("제품과 사이즈가 같은 경우");
            CartProduct cartProduct = maybeItem.get();
            cartProduct.addAmount(newItem);
            return;
        }
        cartProducts.add(newItem);
    }

    private Optional<CartProduct> findSameItem(CartProduct newItem) {
        return cartProducts.stream()
                .filter(p -> p.equals(newItem))
                .findFirst();
    }

    public void delete(CartProduct target) {
        log.debug("Before cart Size : {}", cartProducts.size());
        cartProducts.remove(target);
        log.debug("After cart Size : {}", cartProducts.size());
    }

    public int getSize() {
        return cartProducts.size();
    }

    public int getTotalPrice() {
        return cartProducts.stream()
                .mapToInt(CartProduct::getPrice)
                .sum();
    }

    public List<CartProduct> getCartProducts() {
        return Collections.unmodifiableList(cartProducts);
    }

    public List<OrderItem> toOrderItems(List<Coupon> coupons) {
        int index = 0;
        List<OrderItem> orderItems = new ArrayList<>();
        OrderItem orderItem;

        for (CartProduct product: cartProducts){
            orderItem = product.toOrderItem(coupons.get(index));
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartProducts=" + cartProducts +
                '}';
    }
}
