package dunn.dunnshop.service;

import dunn.dunnshop.entity.Users;
import dunn.dunnshop.dto.UserDto;
import dunn.dunnshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Users saveUser(Users users) {
        return userRepository.save(users);
    }

    public List<Users> saveListUser(List<Users> usersList) {
        List<Users> users = userRepository.saveAll(usersList);
        return users;
    }

    public List<UserDto> getAllUsers() {
        List<Users> usersList = userRepository.findAll();

        return usersList.stream().map(
                user -> new UserDto(user.getUserId(), user.getPassword(), user.getName(), user.getPhone(), user.getEmail(), user.getAddress())).toList();
    }
}
