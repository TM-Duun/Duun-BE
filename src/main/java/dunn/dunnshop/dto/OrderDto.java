package dunn.dunnshop.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderDto {
    private Long userid;
    private List<OrderDetailDto> orderDetailDtoList;

    @Builder
    private OrderDto(Long userid, List<OrderDetailDto> orderDetailDtoList) {
        this.userid = userid;
        this.orderDetailDtoList = orderDetailDtoList;
    }
}
