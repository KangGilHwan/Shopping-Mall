package riverway.domain.cart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart {

    private static final Logger log = LoggerFactory.getLogger(Cart.class);

    private List<CartProduct> cartProducts = new ArrayList<>();

    public Cart() {
    }

    public void addCart(CartProduct newItem) {
        for (CartProduct product : cartProducts) {
            if (product.equals(newItem)) {
                log.debug("제품과 사이즈가 같은 경우");
                product.addAmount(newItem);
                return;
            }
        }
        cartProducts.add(newItem);
    }

    public void delete(CartProduct target) {
        log.debug("Before cart Size : {}", cartProducts.size());
        cartProducts.remove(target);
        log.debug("After cart Size : {}", cartProducts.size());
    }

    public int getTotalPrice() {
        return cartProducts.stream()
                .mapToInt(CartProduct::getPrice)
                .sum();
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartProducts=" + cartProducts +
                '}';
    }
}
