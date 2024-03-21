package dunn.dunnshop.controller;

import dunn.dunnshop.dto.OrderDto;
import dunn.dunnshop.entity.OrderDetails;
import dunn.dunnshop.entity.Orders;
import dunn.dunnshop.entity.Users;
import dunn.dunnshop.request.OrderRequest;
import dunn.dunnshop.response.OrderResponse;
import dunn.dunnshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 장바구니 조회하기
//    @GetMapping("/order/{id}")


    @PostMapping("/order")
    public OrderResponse orderForm(@RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = orderService.savedOrder(orderRequest);

        System.out.println("controller -> orders : " + orderResponse);
        return orderResponse;
    }
}
