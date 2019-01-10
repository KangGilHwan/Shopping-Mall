package riverway.web;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import riverway.domain.Coupon;
import riverway.dto.UserDto;
import support.test.AcceptanceTest;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ApiCouponAcceptanceTest extends AcceptanceTest {

    private static final Logger log = LoggerFactory.getLogger(ApiCouponAcceptanceTest.class);

    @Test
    public void create_coupon() {
        Coupon coupon = new Coupon("name", 3000);

        String location = createResource("/api/coupons", coupon, basicAuthTemplateSeller());
        Coupon dbCoupon = basicAuthTemplateSeller().getForObject(location, Coupon.class);

        assertThat(coupon, is(dbCoupon));
    }

    @Test
    public void create_coupon_no_admin() {
        Coupon coupon = new Coupon("name2", 4000);

        ResponseEntity<String> response = basicAuthTemplateConsumer().postForEntity("/api/coupons", coupon, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.FORBIDDEN));
    }

    @Test
    public void show_coupon_no_admin() {
        Coupon coupon = new Coupon("name3", 3000);

        String location = createResource("/api/coupons", coupon, basicAuthTemplateSeller());
        ResponseEntity<String> response = basicAuthTemplateConsumer().getForEntity(location, String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.FORBIDDEN));
    }

    @Test
    public void issue_coupon() {
        Coupon coupon = new Coupon("name", 3000);
        String location = createResource("/api/coupons", coupon, basicAuthTemplateSeller());

        HttpEntity<MultiValueMap<String, Object>> request = HtmlFormDataBuilder.urlEncodedForm().build();
        ResponseEntity<String> response = basicAuthTemplateSeller().postForEntity(location + "/users/1", request, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        UserDto user = getResource("/api/users/1", UserDto.class);
        log.debug("유저의 쿠폰들 : {}", user.getCoupons());
    }

    @Test
    public void issue_coupon_all_users() {
        Coupon coupon = new Coupon("name", 3000);
        String location = createResource("/api/coupons", coupon, basicAuthTemplateSeller());

        HttpEntity<MultiValueMap<String, Object>> request = HtmlFormDataBuilder.urlEncodedForm().build();
        ResponseEntity<String> response = basicAuthTemplateSeller().postForEntity(location + "/users", request, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        UserDto user = getResource("/api/users/1", UserDto.class);
        log.debug("유저의 쿠폰들 : {}", user.getCoupons());
    }
}
