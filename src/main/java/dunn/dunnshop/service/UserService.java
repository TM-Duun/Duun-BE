package dunn.dunnshop.service;

import dunn.dunnshop.domain.User;
import dunn.dunnshop.dto.UserDto;
import dunn.dunnshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;


//@NoArgsConstructor
//@AllArgsConstructor
@RequiredArgsConstructor //final 키워드 있어야함.
@Service
public class UserService {
    private final UserRepository userRepository; //의존 관계 주입

    //새로운 유저 생성
    public void save(User user){
        userRepository.save(user);
    }

    // 유저 정보 삭제
    public void remove(Long id) {
        final User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }

    // 특정 유저 출력
    public UserDto findUser(Long id){
        final User user = userRepository.findById(id).orElseThrow();
        return UserDto.from(user);
    }

    // 모드 유저 정보 출력
    public List<UserDto> findAllUser() {
        final List<User> allUser = userRepository.findAll();
        return allUser.stream().map(UserDto::from).toList();
    }

    // + 유저 정보 수정

}
