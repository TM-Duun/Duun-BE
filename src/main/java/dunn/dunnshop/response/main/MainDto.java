package dunn.dunnshop.response.main;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MainDto {
    private Long itemId;
    private String img;

    @Builder
    public MainDto(Long itemId, String img) {
        this.itemId = itemId;
        this.img = img;
    }
}
