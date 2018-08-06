package riverway.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import riverway.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{
}
