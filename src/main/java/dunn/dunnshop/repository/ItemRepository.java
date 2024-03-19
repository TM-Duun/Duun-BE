package dunn.dunnshop.repository;

import dunn.dunnshop.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Items, Long> {
}
