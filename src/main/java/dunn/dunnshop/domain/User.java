package dunn.dunnshop.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "user_id", nullable = false, length = 20)
    private String userId;
    @Column(nullable = false, length = 20)
    private String password;
    @Column(nullable = false, length = 5)
    private String username;
    @Column(name = "phone_number", nullable = false, length = 15)
    private String phone;
    @Column(nullable = false, length = 50)
    private String email;
    @Column(nullable = false, length = 100)
    private String address;

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();
}
