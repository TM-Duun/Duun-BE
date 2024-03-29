package dunn.dunnshop.service;

import dunn.dunnshop.dto.UserDto;
import dunn.dunnshop.dto.request.UserRequest;
import dunn.dunnshop.dto.response.user.MyPageDto;
import dunn.dunnshop.entity.OrderDetails;
import dunn.dunnshop.entity.Orders;
import dunn.dunnshop.entity.Users;
import dunn.dunnshop.repository.OrderRepository;
import dunn.dunnshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    // 단일 데이터 저장
    public UserDto saveOneUser(UserDto userDto) {

        Users saveUser = Users.builder()
                .userId(userDto.getUserId())
                .password(userDto.getPassword())
                .name(userDto.getName())
                .phone(userDto.getPhone())
                .email(userDto.getEmail())
                .address(userDto.getAddress())
                .build();

        userRepository.save(saveUser);

        UserDto returnDto = UserDto.builder()
                .userId(saveUser.getUserId())
                .password(saveUser.getPassword())
                .name(saveUser.getName())
                .phone(saveUser.getPhone())
                .email(saveUser.getEmail())
                .address(saveUser.getAddress())
                .build();

        return returnDto;
    }

    // 2개 이상의 데이터 저장
    public List<Users> saveListUser(List<Users> usersList) {
        List<Users> users = userRepository.saveAll(usersList);
        return users;
    }

    public List<UserDto> getAllUsers() {
        List<Users> usersList = userRepository.findAll();

        return usersList.stream().map(
                user -> new UserDto(user.getUserId(), user.getPassword(), user.getName(), user.getPhone(), user.getEmail(), user.getAddress())).toList();
    }

    // GET /mypage 마이페이지 정보 GET
    public MyPageDto mypageForm(Long id) {
        Optional<Orders> searchUser = orderRepository.findById(id);

        List<OrderDetails> orderItems = searchUser.get().getOrderItems();

        MyPageDto myPageDto = MyPageDto.builder()
                .orderItems(orderItems)
                .level("Seed")
                .point(50000L)
                .coupon(2L)
                .build();

        return myPageDto;
    }

    // 중복 아이디 검사
    public boolean duplicateId(String userId) {
        Users findUser = userRepository.findByUserId(userId);

        if (findUser != null) {
            return true;
        } else {
            return false;
        }
    }
}
