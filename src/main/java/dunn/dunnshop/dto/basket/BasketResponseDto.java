package dunn.dunnshop.dto.basket;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class BasketResponseDto {
    private List<BasketDto> basketDtoList;

    @Builder
    private BasketResponseDto(Long id, List<BasketDto> basketDtoList) {
        this.basketDtoList = basketDtoList;
    }
}
