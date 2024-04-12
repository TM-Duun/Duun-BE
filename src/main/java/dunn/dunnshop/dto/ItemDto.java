package dunn.dunnshop.dto;

import dunn.dunnshop.domain.Item;
import dunn.dunnshop.domain.ItemDescription;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ItemDto {
    private Long id;
    private String itemName;
    private Long itemPrice;
    private ItemDescription itemDescription;
    private String itemCategory;
    private String img;
    private LocalDateTime createdAt;

    @Builder
    private ItemDto(Long id, String itemName, Long itemPrice, ItemDescription itemDescription, String itemCategory, String img, LocalDateTime createdAt) {
        this.id = id;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
        this.itemCategory = itemCategory;
        this.img = img;
        this.createdAt = createdAt;
    }


    public static ItemDto from(Item item){
        return ItemDto.builder()
                .id(item.getId())
                .itemName(item.getName())
                .itemPrice(item.getPrice())
                .itemDescription(item.getDescription())
                .itemCategory(item.getCategory())
                .img(item.getImg())
                .createdAt(item.getCreatedAt())
                .build();
    }

}
