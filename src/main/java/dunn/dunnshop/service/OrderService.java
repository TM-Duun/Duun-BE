package dunn.dunnshop.service;

import dunn.dunnshop.dto.OrderDetailDto;
import dunn.dunnshop.dto.OrderDto;
import dunn.dunnshop.entity.Items;
import dunn.dunnshop.entity.OrderDetails;
import dunn.dunnshop.entity.Orders;
import dunn.dunnshop.entity.Users;
import dunn.dunnshop.repository.ItemRepository;
import dunn.dunnshop.repository.OrderDetailRepository;
import dunn.dunnshop.repository.OrderRepository;
import dunn.dunnshop.repository.UserRepository;
import jakarta.persistence.criteria.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public Orders savedOrder(OrderDto orderDto) {
        Users users = userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        System.out.println("users : " + users.getId());

        Orders orders = new Orders();

        orders.setUsers(users);
        orders.setOrderDate(LocalDateTime.now());
//
//        Orders printOrders = orderRepository.save(orders);
//        System.out.println("printOrders : " + printOrders.getUsers().getId());

        List<OrderDetails> orderItems = orderDto.getOrderItems().stream()
                .map(orderItem -> {
                    // 상품 정보 조회
                    Items item = itemRepository.findById(orderItem.getItemId())
                            .orElseThrow(() -> new IllegalArgumentException("Item not found"));

                    // 주문 상세 정보 생성
                    return OrderDetails.builder()
                            .orderId(orders) // 주문 번호 설정
                            .itemId(item) // 상품 번호 설정
                            .quantity(orderItem.getQuantity()) // 수량 설정
                            .build();
                }).toList();
        for (OrderDetails orderItem : orderItems) {
            System.out.println("orderItem : " + orderItem.getItemId());
        }

        orders.setOrderItems(orderItems);
        Orders saveOrders = orderRepository.save(orders);


        orderDetailRepository.saveAll(orderItems);

        // 주문 객체에 주문 상세 정보 추가
//        printOrders.setOrderItems(orderItems);
//        System.out.println("최종 printOrder : " + printOrders.getOrderItems());

        return saveOrders;
    }
}
