package dunn.dunnshop.dto.main;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MainResponseDto {
    List<MainDto> mainDtos;


    @Builder
    public MainResponseDto(List<MainDto> mainDtos) {
        this.mainDtos = mainDtos;
    }
}
