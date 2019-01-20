package riverway.web;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import riverway.domain.Role;
import riverway.dto.UserDto;
import support.test.AcceptanceTest;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ApiUserAcceptanceTest extends AcceptanceTest {

    private static final Logger log = LoggerFactory.getLogger(ApiUserAcceptanceTest.class);

    @Test
    public void create_consumer() {
        UserDto signUpUser = creatConsumer("riverway");
        ResponseEntity<String> response = template.postForEntity("/api/users", signUpUser, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

        String location = response.getHeaders().getLocation().getPath();
        UserDto dbUser = template.getForObject(location, UserDto.class);
        log.debug("Saved User : {}", dbUser);
        assertThat(signUpUser, is(dbUser));
        assertThat(dbUser.getRole(), is(Role.USER));
    }

    public UserDto creatConsumer(String username) {
        UserDto signUpUser = UserDto.build()
                .setUsername(username)
                .setEmail("test@naver.com")
                .setPassword("123456")
                .setPhoneNumber("01012345678");
        return signUpUser;
    }

    @Test
    public void findUser_no_user() {
        ResponseEntity<String> response = template.getForEntity("/api/users/200", String.class);
        log.debug("Saved User : {}", response.getBody());
        assertThat(response.getBody(), is("데이터를 찾을 수 없습니다."));
    }

    @Test
    public void login_success() {
        UserDto signUpUser = creatConsumer("riverway3");
        ResponseEntity<String> response = template.postForEntity("/api/users", signUpUser, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

        HttpEntity<MultiValueMap<String, Object>> request = login("riverway3", "123456");
        response = template.postForEntity("/api/users/login", request, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        log.debug("response : {}", response);
    }

    @Test
    public void login_다른_비밀번호() {
        UserDto signUpUser = creatConsumer("riverway4");
        ResponseEntity<String> response = template.postForEntity("/api/users", signUpUser, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

        HttpEntity<MultiValueMap<String, Object>> request = login("riverway4", "1234567");
        response = template.postForEntity("/api/users/login", request, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.UNAUTHORIZED));
        log.debug("response : {}", response);
    }

    public HttpEntity<MultiValueMap<String, Object>> login(String username, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("username", username);
        params.add("password", password);
        return new HttpEntity<>(params, headers);
    }
}