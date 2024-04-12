package dunn.dunnshop.controller;

import dunn.dunnshop.domain.Order;
import dunn.dunnshop.dto.OrderDto;
import dunn.dunnshop.dto.OrderRequestDto;
import dunn.dunnshop.dto.basket.BasketResponseDto;
import dunn.dunnshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value= "/orders")
public class OrderController {

    private final OrderService orderService;
    @PostMapping("")
    public void createOrder(@RequestBody OrderRequestDto orderRequestDto){
        orderService.create(orderRequestDto);
    }

    @GetMapping("")
    public List<OrderDto> findAllOrder() {
        return orderService.findAllOrder();
    }

    /*
    ==================== Basket ====================
     */
    @GetMapping("/{id}")
    public BasketResponseDto findBasketById(@PathVariable ("id") Long id){
        return orderService.findBasketById(id);
    }

}
