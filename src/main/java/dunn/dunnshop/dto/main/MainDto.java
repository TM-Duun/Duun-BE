package dunn.dunnshop.dto.main;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MainDto {
    private long itemId;
    private String img;

    @Builder
    private MainDto(long itemId, String name, String img) {
        this.itemId = itemId;
        this.img = img;
    }
}
