package dunn.dunnshop.request;

import dunn.dunnshop.dto.OrderDetailDto;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequest {
    private Long userId;
    private List<OrderDetailDto> orderItems;

    public OrderRequest(Long userId, List<OrderDetailDto> orderItems) {
        this.userId = userId;
        this.orderItems = orderItems;
    }
}
