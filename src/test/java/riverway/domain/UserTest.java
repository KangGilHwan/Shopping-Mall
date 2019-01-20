package riverway.domain;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTest {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void matchPassword() {
        String password = "12345";
        String encodedPassword = passwordEncoder.encode(password);

        User user = createUser("riverway", encodedPassword, Role.USER);
        assertTrue(user.matchPassword(password, passwordEncoder));
    }

    @Test
    public void isSeller() {
        User user = createUser("bob", "12345", Role.ADMIN);
        assertTrue(user.isSeller());
    }

    @Test
    public void isSeller_fail() {
        User user = createUser("Tom", "12345", Role.USER);
        assertFalse(user.isSeller());
    }

    public User createUser(String username, String password, Role role) {
        return new User(username, password, "rlfghks@naver.com", "01012345678", 1L, null, role);
    }
}