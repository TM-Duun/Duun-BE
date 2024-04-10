package dunn.dunnshop.repository;

import dunn.dunnshop.domain.Item;
import dunn.dunnshop.domain.ItemDescription;
import dunn.dunnshop.dto.ItemDto;
import dunn.dunnshop.dto.main.MainDto;
import dunn.dunnshop.dto.main.MainResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;


public interface ItemsRepository extends JpaRepository<Item,Long> {

//    @Query("select i from Item i join fetch i.description where i.category = :category order by i.description.like desc limit 4")
//    @Query("""
//            select i
//            from Item i
//            join fetch i.description
//            where i.category = :category
//            order by i.description.like desc
//            limit 4
//            """)
//    List<ItemDto> findByCategoryTop4(@Param("category") String category);


     // from and join -> where -> group by -> having -> select -> order by -> limit
    @Query("""
            select new dunn.dunnshop.dto.main.MainDto(i.id as itemId, i.img as img) 
            from Item i 
            join i.description 
            where i.category = :category 
            order by i.description.like desc 
            limit 4
            """)
    List<MainDto> findByCategoryTop4(@Param("category") String category);




}