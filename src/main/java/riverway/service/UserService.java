package riverway.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riverway.domain.SocialCode;
import riverway.domain.User;
import riverway.domain.repository.UserRepository;
import riverway.dto.UserDto;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(UserDto signUpUser) {
        return userRepository.save(signUpUser.toUser());
    }

    public User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Optional<User> loginKakao(Long id) {
        return userRepository.findBySocialIdAndSocialCode(id, SocialCode.KAKAO);
    }
}
