package dunn.dunnshop.repository;

import dunn.dunnshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long>{

    @Query("""
            select count(u) > 0
            from User u 
            where u.userId = :userId
            """)
    boolean countByUserId(@Param("userId") String userId);

}