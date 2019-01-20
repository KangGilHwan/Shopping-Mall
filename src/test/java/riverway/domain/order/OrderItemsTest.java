package riverway.domain.order;

import org.junit.Before;
import org.junit.Test;
import riverway.domain.Coupon;
import riverway.domain.User;
import riverway.domain.cart.Option;
import riverway.domain.cart.Size;
import riverway.domain.product.Category;
import riverway.domain.product.Product;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderItemsTest {

    private OrderItems orderItems;

    @Before
    public void setUp() {
        List<OrderItem> items = new ArrayList<>();

        Coupon coupon = new Coupon("2월 쿠폰", 6000);
        OrderItem item = createOrderitem("흰색 티셔츠", 15000, 1, coupon);
        items.add(item);

        item = createOrderitem("양말", 2000, 2, null);
        items.add(item);
        orderItems = new OrderItems(items);
    }

    @Test
    public void calculateTotalPrice() {
        assertThat(orderItems.calculateTotalPrice()).isEqualTo(13000);
    }

    @Test
    public void joinItemNames() {
        assertThat(orderItems.joinItemNames()).isEqualTo("흰색 티셔츠, 양말");
    }

    public OrderItem createOrderitem(String name, int price, int number, Coupon coupon) {
        Product product = new Product(name, price, "기본 아이템", Category.MEN, new User());
        Option option = new Option(Size.M, number);
        return new OrderItem(product, option, coupon);
    }
}