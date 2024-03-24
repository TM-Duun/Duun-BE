package dunn.dunnshop.dto.category;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CategoryResponseDto {
    List<CategoryDto> categoryDtos;

    @Builder
    private CategoryResponseDto(List<CategoryDto> categoryDtos) {
        this.categoryDtos = categoryDtos;
    }
}
