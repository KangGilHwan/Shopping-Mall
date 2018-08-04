package riverway.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import riverway.domain.SocialCode;
import riverway.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

   Optional<User> findByUsername(String username);
   Optional<User> findBySocialIdAndSocialCode(Long id, SocialCode socialCode);
}
