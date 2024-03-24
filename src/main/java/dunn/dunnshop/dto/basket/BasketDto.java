package dunn.dunnshop.dto.basket;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BasketDto {
    private Long itemId;
    private String itemName;
    private Long quantity;

    @Builder
    private BasketDto(Long itemId, String itemName, Long quantity) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
    }
}
