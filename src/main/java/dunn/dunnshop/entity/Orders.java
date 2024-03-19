package dunn.dunnshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 파라미터 없는 생성자 만듬
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @Column
    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "orderId")
    private List<OrderDetails> orderDetails = new ArrayList<>();

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Builder
    public Orders(Users users, LocalDateTime orderDate, List<OrderDetails> orderDetails) {
        this.users = users;
        this.orderDate = orderDate;
        this.orderDetails = orderDetails;
    }
}
