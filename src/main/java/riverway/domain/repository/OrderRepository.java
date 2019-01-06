package riverway.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import riverway.domain.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
