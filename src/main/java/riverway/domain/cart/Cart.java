package riverway.domain.cart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private static final Logger log = LoggerFactory.getLogger(Cart.class);

    private List<CartProduct> cartProducts = new ArrayList<>();

    public Cart() {
    }

    public void addCart(CartProduct newItem) {
        for (CartProduct product : cartProducts) {
            if (product.equals(newItem)) {
                log.debug("제품 사이즈가 같은 경우");
                product.addAmount(newItem);
                return;
            }
        }
        cartProducts.add(newItem);
    }

    public int getTotalPrice() {
        return cartProducts.stream()
                .mapToInt(CartProduct::getPrice)
                .sum();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartProducts=" + cartProducts +
                '}';
    }
}
