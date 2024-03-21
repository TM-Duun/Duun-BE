package dunn.dunnshop.response.cart;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CartResponseDto {
    private String name;
    private String size;
    private String color;
    private Long price;

    @Builder
    public CartResponseDto(String name, String size,
                           String color, Long price) {
        this.name = name;
        this.size = size;
        this.color = color;
        this.price = price;
    }
}
