package dunn.dunnshop.repository;

import dunn.dunnshop.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ItemRepository extends JpaRepository<Items, Long> {
    // category 를 기준으로 찾기
    List<Items> findByCategory(String category);
}
