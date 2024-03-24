package dunn.dunnshop.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "user_id",nullable = false,length = 20, unique = true)
    private String userId;

    @Column(name = "password",nullable = false, length = 20)
    private String password;

    @Column(name = "user_name",nullable = false, length = 5)
    private String userName;

    @Column(name = "phone_number",nullable = false, length = 15)
    private String phoneNumber;

    @Column(name = "email",nullable = false, length = 50, unique=true)
    private String email;

    @Column(name = "address",nullable = false, length = 100)
    private String address;

    //default 값 존재
    @Column(name="level",length=10)
    @ColumnDefault("'새싹'")
    private String level;

    //default 값 존재
    @Column(name="point")
    @ColumnDefault("30000")
    private Long point;

    // 아직 필요없는듯?
//    @OneToMany(mappedBy = "user")
//    private List<Order> orderList = new ArrayList<>();

    @Builder // 1. 우리가 쓸 생성자가 아니여서 private  2. @Setter 대신에 Builder 패턴으로
    private User(String userId, String password, String userName, String phoneNumber, String email, String address) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }
}
