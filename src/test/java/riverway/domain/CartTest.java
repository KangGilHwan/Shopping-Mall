package riverway.domain;

import org.junit.Before;
import org.junit.Test;
import riverway.domain.cart.Cart;
import riverway.domain.cart.CartProduct;
import riverway.domain.cart.Option;
import riverway.domain.cart.Size;
import riverway.domain.product.Category;
import riverway.domain.product.Product;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CartTest {

    private CartProduct cartProduct;

    @Before
    public void setUp() {
        Option option = new Option(Size.XL, 3);
        Product product = new Product("모자", 2500, "cap", Category.ACCESSORIES, new User());
        cartProduct = new CartProduct(product, option);
    }

    @Test
    public void 장바구니_상품_1개_가격() {
        assertThat(cartProduct.getPrice(), is(7500));
    }

    @Test
    public void 장바구니_전체_가격() {
        Cart cart = new Cart();

        Option option = new Option(Size.XL, 5);
        Product product = new Product("모자", 2500, "cap", Category.ACCESSORIES, new User());
        CartProduct newItem = new CartProduct(product, option);
        cart.addCart(newItem);

        option = new Option(Size.L, 3);
        product = new Product("바지", 6000, "pants", Category.MEN, new User());
        newItem = new CartProduct(product, option);
        cart.addCart(newItem);

        assertThat(cart.getTotalPrice(), is(30500));
    }

    @Test
    public void addCart_상품_사이즈_같을때() {
        Cart cart = new Cart();
        cart.addCart(cartProduct);

        Option option = new Option(Size.XL, 5);
        Product product = new Product("모자", 2500, "cap", Category.ACCESSORIES, new User());
        CartProduct newItem = new CartProduct(product, option);

        cart.addCart(newItem);
        assertThat(cart.getSize(), is(1));
        assertThat(cart.getTotalPrice(), is(20000));
    }

    @Test
    public void addCart_상품_사이즈_다를때() {
        Cart cart = new Cart();
        cart.addCart(cartProduct);

        Option option = new Option(Size.L, 5);
        Product product = new Product("모자", 2500, "cap", Category.ACCESSORIES, new User());
        CartProduct newItem = new CartProduct(product, option);

        cart.addCart(newItem);
        assertThat(cart.getSize(), is(2));
    }

    @Test
    public void addAmount() {
        Option product = new Option(Size.L, 2);
        Option sameProductOption = new Option(Size.L, 3);
        product.addAmount(sameProductOption);

        assertThat(product.getAmount(), is(5));
    }
}
