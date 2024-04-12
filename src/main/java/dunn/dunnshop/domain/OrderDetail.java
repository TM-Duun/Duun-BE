package dunn.dunnshop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //기본생성자
@Table(name="order_details")
public class OrderDetail{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    // nullable yes or no
    private Long quantity;

    @Builder
    private OrderDetail(Order order, Item item, Long quantity) {
        this.item = item;
        setOrders(order);
        this.quantity = quantity;
    }

    //편의성 메서드
    private void setOrders(Order order){
        this.order = order;
        order.addOrderDetailsList(this);
    }

}
