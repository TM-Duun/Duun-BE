package dunn.dunnshop.service;

import dunn.dunnshop.domain.Item;
import dunn.dunnshop.domain.Order;
import dunn.dunnshop.domain.OrderDetail;
import dunn.dunnshop.domain.User;
import dunn.dunnshop.dto.OrderDetailDto;
import dunn.dunnshop.dto.OrderDto;
import dunn.dunnshop.dto.OrderRequestDto;
import dunn.dunnshop.dto.basket.BasketDto;
import dunn.dunnshop.dto.basket.BasketResponseDto;
import dunn.dunnshop.repository.ItemsRepository;
import dunn.dunnshop.repository.OrderRepository;
import dunn.dunnshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemsRepository itemsRepository;

    // 사용자로부터 주문이 들어왔을때
//    @Transactional
    public void create(OrderRequestDto orderRequestDto){

        //orderRequestDto에서 userId로 user 찾기
        final User user = userRepository.findById(orderRequestDto.getUserId()).orElseThrow(()->new IllegalArgumentException());
        final List<OrderDetailDto> orderDetailDtos = orderRequestDto.getOrderDetailDtoList();

//        final List<OrderDetail> orderDetails = new ArrayList<>();

        Order order = Order.builder()
                .user(user)
                .orderDate(LocalDateTime.now())
                .build();

        for (OrderDetailDto orderDetailDto : orderDetailDtos) {
//            orderRepository.findById(orderDetailDto.getOrderId()).ifPresent((o)->new IllegalArgumentException());
            Item item = itemsRepository.findById(orderDetailDto.getItemId()).orElseThrow();

//            final OrderDetail orderDetail =
            OrderDetail.builder()
                        .item(item)
                        .order(order)
                        .quantity(orderDetailDto.getQuantity())
                        .build();
            //orderDetails.add(orderDetail);
        }

        orderRepository.save(order);

    }

    public List<OrderDto> findAllOrder(){
        List<Order> orderlist = orderRepository.findAll();

        List<OrderDto> allOrder = new ArrayList<>();

        for (Order order : orderlist) {
            final List<OrderDetail> orderDetailList = order.getOrderDetailList();

            List<OrderDetailDto> orderDetilDtoList = new ArrayList<>();
            for (OrderDetail orderDetail : orderDetailList) {
                final OrderDetailDto orderDetailDto = OrderDetailDto.builder()
                                                                .itemId(orderDetail.getItem().getId())
                                                                .quantity(orderDetail.getQuantity())
                                                                .build();
                orderDetilDtoList.add(orderDetailDto);
            }
            final OrderDto orderDto = OrderDto.builder()
                                            .userid(order.getUser().getId())
                                            .orderDetailDtoList(orderDetilDtoList)
                                            .build();
            allOrder.add(orderDto);
        }
        return allOrder;
    }

    /*
    =============================== Bascket ===============================
     */

    //특정 User의 장바구니 불러오기
    public BasketResponseDto findBasketById(Long id){
        final List<Order> userOrders = orderRepository.findAll().stream()
                .filter(order -> id.equals(order.getUser().getId())).collect(Collectors.toList());

        List<BasketDto> basketDtoList = new ArrayList<>();

        for (Order userOrder : userOrders) {
            final List<OrderDetail> orderDetailList = userOrder.getOrderDetailList();

            for (OrderDetail orderDetail : orderDetailList) {
                BasketDto basketDto = BasketDto.builder()
                                                .itemId(orderDetail.getItem().getId())
                                                .itemName(orderDetail.getItem().getName())
                                                .quantity(orderDetail.getQuantity())
                                                .build();

                basketDtoList.add(basketDto);
            }
        }

        BasketResponseDto response = BasketResponseDto.builder()
                                                        .basketDtoList(basketDtoList)
                                                        .build();

        return response;
    }


    // 아직 미정미정
    public void deleteBaskeItem(Long id) {

    }

}
