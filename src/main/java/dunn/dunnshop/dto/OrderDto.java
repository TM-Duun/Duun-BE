package dunn.dunnshop.dto;

import dunn.dunnshop.entity.OrderDetails;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderDto {
    private Long userId;
    private List<OrderDetailDto> orderItems;

    public OrderDto(Long userId, List<OrderDetailDto> orderItems) {
        this.userId = userId;
        this.orderItems = orderItems;
    }
}
