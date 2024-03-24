package dunn.dunnshop.dto;


import dunn.dunnshop.domain.OrderDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderRequestDto {
    private Long userId;
    private List<OrderDetailDto> orderDetailDtoList;

    @Builder
    public OrderRequestDto(Long userId, List<OrderDetailDto> orderDetailDtoList) {
        this.userId = userId;
        this.orderDetailDtoList = orderDetailDtoList;
    }
}
