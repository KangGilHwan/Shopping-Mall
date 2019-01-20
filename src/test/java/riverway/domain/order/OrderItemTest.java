package riverway.domain.order;

import org.junit.Test;
import riverway.domain.Coupon;
import riverway.domain.User;
import riverway.domain.cart.Option;
import riverway.domain.cart.Size;
import riverway.domain.product.Category;
import riverway.domain.product.Product;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderItemTest {

    @Test
    public void calculatePriceNoCoupon() {
        Product product = new Product("겨울 점퍼", 30000, "따뜻한 외투", Category.MEN, new User());
        Option option = new Option(Size.S, 3);
        OrderItem orderItem = new OrderItem(product, option, null);

        assertThat(orderItem.calculatePriceNoCoupon()).isEqualTo(90000);
    }

    @Test
    public void calculatePriceExistCoupon() {
        Product product = new Product("흰색 티셔츠", 15000, "밝은 티셔츠", Category.MEN, new User());
        Option option = new Option(Size.M, 1);
        Coupon coupon = new Coupon("2월 쿠폰", 6000);
        OrderItem orderItem = new OrderItem(product, option, coupon);

        assertThat(orderItem.calculatePrice()).isEqualTo(9000);
    }
}