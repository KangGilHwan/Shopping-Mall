package riverway.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import riverway.domain.Product;
import riverway.domain.cart.Cart;
import riverway.domain.cart.CartProduct;
import riverway.domain.cart.Option;
import riverway.service.ProductService;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/products/{id}/cart")
public class ApiCartController {

    private static final Logger log = LoggerFactory.getLogger(ApiCartController.class);

    @Autowired
    private ProductService productService;

    @PostMapping("")
    public ResponseEntity<Cart> addCart(@PathVariable Long id, @RequestBody Option option, HttpSession session) {
        log.debug("Option : {}", option);
        Product product = productService.findItem(id);
        CartProduct cartProduct = new CartProduct(product, option);
        Cart cart = HttpSessionUtils.getCartFromSession(session);
        cart.addCart(cartProduct);
        log.debug("CartProduct : {}", cartProduct);
        log.debug("CartList : {}", cart);
        return ResponseEntity.ok().body(cart);
    }

    @GetMapping("")
    public ResponseEntity<Cart> showCart(@PathVariable Long id, HttpSession session){
        Cart cart = HttpSessionUtils.getCartFromSession(session);
        log.debug("CartList : {}", cart);
        return ResponseEntity.ok().body(cart);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestBody Option option, HttpSession session) {
        log.debug("Option : {}", option);
        Product product = productService.findItem(id);
        CartProduct cartProduct = new CartProduct(product, option);
        Cart cart = HttpSessionUtils.getCartFromSession(session);
        cart.delete(cartProduct);
        log.debug("CartList : {}", cart);
        return ResponseEntity.ok().build();
    }
}
