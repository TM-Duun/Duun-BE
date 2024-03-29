package dunn.dunnshop.dto;

import dunn.dunnshop.entity.OrderDetails;
import dunn.dunnshop.entity.Users;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderResponse {
    private Users users;
    private LocalDateTime orderDate;
    private List<OrderDetails> orderItems = new ArrayList<>();

    @Builder
    public OrderResponse(Users users, LocalDateTime orderDate, List<OrderDetails> orderItems) {
        this.users = users;
        this.orderDate = orderDate;
        this.orderItems = orderItems;
    }
}
