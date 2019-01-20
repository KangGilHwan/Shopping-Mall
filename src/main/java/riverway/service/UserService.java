package riverway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import riverway.domain.SocialCode;
import riverway.domain.User;
import riverway.domain.repository.UserRepository;
import riverway.dto.UserDto;
import riverway.exception.UnAuthenticationException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public User register(UserDto signUpUser) {
        String encodedPassword = bCryptPasswordEncoder.encode(signUpUser.getPassword());
        return userRepository.save(signUpUser.setPassword(encodedPassword).toConsumer());
    }

    public User socialRegister(UserDto signUpUser) {
        return userRepository.save(signUpUser.toConsumer());
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Optional<User> loginKakao(Long id) {
        return userRepository.findBySocialIdAndSocialCode(id, SocialCode.KAKAO);
    }

    public User login(String username, String password) throws UnAuthenticationException {
        return userRepository.findByUsername(username)
                .filter(user -> user.matchPassword(password, bCryptPasswordEncoder))
                .orElseThrow(() -> new UnAuthenticationException("비밀번호가 올바르지 않습니다."));
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(u -> u.toUserDto()).collect(Collectors.toList());
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
