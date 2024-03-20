package dunn.dunnshop.controller;

import dunn.dunnshop.dto.OrderDto;
import dunn.dunnshop.entity.OrderDetails;
import dunn.dunnshop.entity.Orders;
import dunn.dunnshop.entity.Users;
import dunn.dunnshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

//    @ResponseBody
//    @GetMapping("/order/all")
//    public List<OrderDto> getAllOrder() {
//        return orderService.getAllOrder();
//    }

    @ResponseBody
    @PostMapping("/order")
    public Orders orderForm(@RequestBody OrderDto orderDto) {
        Orders orders = orderService.savedOrder(orderDto);

        System.out.println("controller -> orders : " + orders);
        return orders;
    }
}
