package dunn.dunnshop.dto;

import dunn.dunnshop.entity.Items;
import lombok.Getter;

@Getter
public class OrderDetailDto {
    private Long itemId;
    private Long quantity;

    public OrderDetailDto(Long itemId, Long quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }
}
