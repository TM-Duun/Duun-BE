package dunn.dunnshop.dto.response.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dunn.dunnshop.entity.OrderDetails;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MyPageDto {
    private List<OrderDetails> orderItems;
    private String level;
    private Long point;
    private Long coupon;

    @Builder
    public MyPageDto(List<OrderDetails> orderItems, String level,
                     Long point, Long coupon) {
        this.orderItems = orderItems;
        this.level = level;
        this.point = point;
        this.coupon = coupon;
    }

}