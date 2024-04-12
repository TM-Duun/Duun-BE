package dunn.dunnshop.repository;

import dunn.dunnshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long>{

//    @Query("""
//            select u
//            from User u
//            where u.userId = :userId
//            limit 1
//            """)
    @Query(value = """
            select exists(select 1 from User u where u.userId = :userId)
            """,nativeQuery = true)
    boolean countByUserId(@Param("userId") String userId);

    boolean existsByUserId(String userId);
}