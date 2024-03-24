package dunn.dunnshop.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //기본생성자
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="order_date",nullable = false)
    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();

    @Builder  //@setter 대용
    private Order(User user, LocalDateTime orderDate) {
        this.user= user;
        this.orderDate = orderDate;
        //this.orderDetailList = orderDetailList; //  질문1. 이래도 되는가??
    }

    //편의성 메서드
    public void addOrderDetailsList(OrderDetail orderDetail){
        orderDetailList.add(orderDetail);
    }
}
