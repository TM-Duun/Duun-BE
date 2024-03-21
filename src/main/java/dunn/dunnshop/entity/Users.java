package dunn.dunnshop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 파라미터 없는 생성자 만듬
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20,unique = true, nullable = false)
    private String userId;

    @Column(length = 20, nullable = false)
    private String password;

    @Column(length = 5, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String phone;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String address;

    @Builder
    private Users(String userId, String password, String name, String phone, String email, String address) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
}
