package dunn.dunnshop.repository;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import dunn.dunnshop.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.Map;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
}
