package riverway.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import riverway.Exception.UnAuthenticationException;
import riverway.domain.SocialCode;
import riverway.domain.User;
import riverway.domain.repository.UserRepository;
import riverway.dto.UserDto;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public User register(UserDto signUpUser) {
        String encodedPassword = bCryptPasswordEncoder.encode(signUpUser.getPassword());
        return userRepository.save(signUpUser.setPassword(encodedPassword).toUser());
    }

    public User socialRegister(UserDto signUpUser) {
        return userRepository.save(signUpUser.toUser());
    }

    public User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Optional<User> loginKakao(Long id) {
        return userRepository.findBySocialIdAndSocialCode(id, SocialCode.KAKAO);
    }

    public User login(String username, String password) throws UnAuthenticationException{
        return userRepository.findByUsername(username)
                .filter(user -> bCryptPasswordEncoder.matches(password, user.getPassword()))
                .orElseThrow(() -> new UnAuthenticationException("비밀번호가 올바르지 않습니다."));
    }
}
