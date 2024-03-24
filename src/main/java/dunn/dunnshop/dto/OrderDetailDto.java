package dunn.dunnshop.dto;

import dunn.dunnshop.domain.OrderDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderDetailDto {
    private Long itemId;
    private Long quantity;

    @Builder
    private OrderDetailDto(Long itemId, Long quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    // OrderDetail -> OrderDetailDto
    public static OrderDetailDto from(OrderDetail orderDetail){
        return OrderDetailDto.builder()
                            .itemId(orderDetail.getItem().getId())
                            .quantity(orderDetail.getQuantity())
                            .build();
    }
}
