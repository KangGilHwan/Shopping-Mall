package riverway.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import riverway.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
}
