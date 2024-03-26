package dunn.dunnshop.dto.response.main;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryDto {
    private Long itemId;
    private String img;
    private String itemName;
    private Long price;

    @Builder
    public CategoryDto(Long itemId, String img, String itemName, Long price) {
        this.itemId = itemId;
        this.img = img;
        this.itemName = itemName;
        this.price = price;
    }
}
