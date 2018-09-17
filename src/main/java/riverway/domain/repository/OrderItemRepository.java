package riverway.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import riverway.domain.order.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
