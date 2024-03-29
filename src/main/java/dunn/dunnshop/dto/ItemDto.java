package dunn.dunnshop.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ItemDto {
    private String name;
    private Long price;
    private String color;
    private String size;
    private String gender;
    private Long likes;
    private String category;
    private String image;
    private LocalDateTime incomeDate;

    @Builder
    public ItemDto(String name, Long price, String color, String size, String gender,
                   Long likes, String category, String image, LocalDateTime incomeDate) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.size = size;
        this.gender = gender;
        this.likes = likes;
        this.category = category;
        this.image = image;
        this.incomeDate = incomeDate;
    }
}
