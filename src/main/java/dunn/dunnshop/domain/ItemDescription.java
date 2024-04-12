package dunn.dunnshop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@NoArgsConstructor
@ToString
public class ItemDescription {
    @Column(name="item_color", nullable = false,length = 10)
    private String color;

    @Column(name="item_size", nullable = false,length = 5)
    private String size;

    @Column( name="item_gender", nullable = false, length = 10)
    private String gender;

    @Column( name="item_Likes", nullable = false)
    private int like;

    @Builder
    private ItemDescription(String color,String size, String gender, int like) {
        this.color = color;
        this.size = size;
        this.gender = gender;
        this.like = like;
    }
}