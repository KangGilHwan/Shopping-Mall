package riverway.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import riverway.domain.Product;

public interface ItemRepository extends JpaRepository<Product, Long>{
}
