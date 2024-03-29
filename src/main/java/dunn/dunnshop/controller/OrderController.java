package dunn.dunnshop.controller;

import dunn.dunnshop.dto.request.OrderRequest;
import dunn.dunnshop.dto.OrderResponse;
import dunn.dunnshop.dto.response.cart.CartResponseDto;
import dunn.dunnshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 장바구니 조회하기
    @GetMapping("/order/{id}")
    public CartResponseDto searchCart(@PathVariable Long id) {
        CartResponseDto cartResponseDto = orderService.searchOrder(id);
        return cartResponseDto;
    }

    // 주문하기
    @PostMapping("/order")
    public OrderResponse orderForm(@RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = orderService.savedOrder(orderRequest);

        System.out.println("controller -> orders : " + orderResponse);
        return orderResponse;
    }

    @DeleteMapping("/order/{id}")
    public void deleteForm(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
    }
}
