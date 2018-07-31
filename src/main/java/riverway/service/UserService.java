package riverway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riverway.domain.User;
import riverway.domain.repository.UserRepository;
import riverway.dto.UserDto;

import javax.persistence.EntityNotFoundException;

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
}
