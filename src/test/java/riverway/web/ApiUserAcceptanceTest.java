package riverway.web;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import riverway.dto.UserDto;
import support.test.AcceptanceTest;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ApiUserAcceptanceTest extends AcceptanceTest {

    private static final Logger log = LoggerFactory.getLogger(ApiUserAcceptanceTest.class);

    @Test
    public void create() {
        UserDto signUpUser = UserDto.build()
                .setUsername("riverway")
                .setEmail("test@naver.com")
                .setPassword("123456")
                .setPhoneNumber("01012345678");

        ResponseEntity<String> response = template.postForEntity("/api/users", signUpUser, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

        String location = response.getHeaders().getLocation().getPath();

        UserDto user = template.getForObject(location, UserDto.class);
        log.debug("Saved User : {}", user);
        assertThat(signUpUser, is(user));
    }

    @Test
    public void show(){

    }
}