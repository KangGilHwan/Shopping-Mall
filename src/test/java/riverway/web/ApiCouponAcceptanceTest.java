package riverway.web;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import riverway.domain.Coupon;
import support.test.AcceptanceTest;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ApiCouponAcceptanceTest extends AcceptanceTest{

    @Test
    public void create_coupon(){
        Coupon coupon = new Coupon("name", 3000);

        String location = createResource("/api/coupons", coupon, basicAuthTemplateSeller());
        Coupon dbCoupon = basicAuthTemplateSeller().getForObject(location, Coupon.class);

        assertThat(coupon, is(dbCoupon));
    }

    @Test
    public void create_coupon_no_admin(){
        Coupon coupon = new Coupon("name2", 4000);

        ResponseEntity<String> response = basicAuthTemplateConsumer().postForEntity("/api/coupons", coupon, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.FORBIDDEN));
    }

    @Test
    public void show_coupon_no_admin(){
        Coupon coupon = new Coupon("name3", 3000);

        String location = createResource("/api/coupons", coupon, basicAuthTemplateSeller());
        ResponseEntity<String> response = basicAuthTemplateConsumer().getForEntity(location, String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.FORBIDDEN));
    }

    @Test
    public void issue_coupon(){

    }
}
