package riverway.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import riverway.exception.UnAuthenticationException;
import riverway.domain.User;
import riverway.dto.UserDto;
import riverway.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {

    private static final Logger log = LoggerFactory.getLogger(ApiUserController.class);
    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<Void> create(@Valid @RequestBody UserDto signUpUser) {
        log.debug("register : {}", signUpUser);
        User savedUser = userService.register(signUpUser);
        log.debug("Login : {}", savedUser);

        URI uri = URI.create("/api/users/" + savedUser.getId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public UserDto show(@PathVariable Long id) {
        log.debug("User : {}",userService.findById(id));
        return userService.findById(id).toUserDto();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(String username, String password, HttpSession session) throws UnAuthenticationException {
        log.debug("username : {} , password : {}", username, password);
        session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, userService.login(username, password));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session){
        session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
        log.debug("Logout!");
        return ResponseEntity.ok().build();
    }
}
