package dunn.dunnshop.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 외부에서 접근하지 못하도록 설정
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩 설정
    @JoinColumn(name = "order_id")
    private Orders orderId;

    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩 설정
    @JoinColumn(name = "item_id")
    private Items itemId;

    @ColumnDefault("2")
    private Long quantity;

    @Builder
    public OrderDetails(Orders orderId, Items itemId, Long quantity) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
    }
}
