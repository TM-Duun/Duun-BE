package dunn.dunnshop.repository;

import dunn.dunnshop.domain.Order;
import dunn.dunnshop.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
