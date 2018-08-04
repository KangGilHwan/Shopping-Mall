package riverway.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import riverway.domain.User;
import riverway.dto.UserDto;
import riverway.service.UserService;

import javax.servlet.http.HttpSession;
import java.net.URI;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {

    private static final Logger log = LoggerFactory.getLogger(ApiUserController.class);
    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<Void> create(@RequestBody UserDto signUpUser, HttpSession session){
        log.debug("register : {}", signUpUser);
        User savedUser = userService.register(signUpUser);
        session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, savedUser);
        log.debug("Login : {}", savedUser);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/api/users/" + savedUser.getId()));
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public UserDto show(@PathVariable Long id){
        return userService.findUser(id).toUserDto();
    }
}
