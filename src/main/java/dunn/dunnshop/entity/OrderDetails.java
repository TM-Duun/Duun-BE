package dunn.dunnshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)      // 외부에서 접근하지 못하도록 설정
public class OrderDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore     // OrderDetails 테이블 안에 orders 부분 출력되지 않게 -> 순환참조 막기 위해
    @ManyToOne(fetch = FetchType.LAZY)      // 지연로딩 설정
    @JoinColumn(name = "order_id")
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)      // 지연로딩 설정
    @JoinColumn(name = "item_id")
    private Items items;

    @Column
    private Long quantity;

    @Builder
    public OrderDetails(Orders orders, Items items, Long quantity) {
        this.orders = orders;
        this.items = items;
        this.quantity = quantity;
    }
}
