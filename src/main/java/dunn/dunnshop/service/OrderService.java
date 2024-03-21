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
import dunn.dunnshop.request.OrderRequest;
import dunn.dunnshop.response.OrderResponse;
import dunn.dunnshop.response.cart.CartResponseDto;
import jakarta.persistence.criteria.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public OrderResponse savedOrder(OrderRequest orderRequest) {
        Users users = userRepository.findById(orderRequest.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("User not found"));

        System.out.println("users : " + users.getId());

        Orders orders = new Orders();   // 새로운 Order 생성
        orders.setUsers(users);         // users 정보 저장
        orders.setOrderDate(LocalDateTime.now());       // 시간 저장

        List<OrderDetails> orderItems = orderRequest.getOrderItems().stream()
                .map(orderItem -> {
                    // 상품 정보 조회
                    Items item = itemRepository.findById(orderItem.getItemId())
                            .orElseThrow(() -> new IllegalArgumentException("Item not found"));

                    // 주문 상세 정보 생성
                    return OrderDetails.builder()
                            .orders(orders) // 주문 번호 설정
                            .items(item) // 상품 번호 설정
                            .quantity(orderItem.getQuantity()) // 수량 설정
                            .build();
                }).toList();

        orders.setOrderItems(orderItems);

        orderRepository.save(orders);
        orderDetailRepository.saveAll(orderItems);

        OrderResponse orderResponse = OrderResponse.builder()
                .users(orders.getUsers())
                .orderDate(LocalDateTime.now())
                .orderItems(orders.getOrderItems()).build();

        return orderResponse;
    }

    public CartResponseDto searchOrder(Long id) {
        Optional<Orders> findOrder = orderRepository.findById(id);
        Orders order = findOrder.get();

        List<OrderDetails> orderItem = order.getOrderItems();

        CartResponseDto cartResponseDto = CartResponseDto.builder()
                .name(order.getOrderItems().get)
                .build();
    }
}
