package dunn.dunnshop.dto;

import lombok.Getter;

@Getter
public class ItemDto {

//    private Long id;
    private String name;
    private int price;
    private String description;
    private String category;

    public ItemDto(String name, int price, String description, String category) {
//        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }
}
