package dunn.dunnshop.dto.category;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryDto {
    private Long itemId;
    private String img;
    private String name;
    private Long price;


    @Builder
    private CategoryDto(Long itemId, String name, Long price, String img) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.img = img;
    }

}
