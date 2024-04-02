package dunn.dunnshop.controller;

import dunn.dunnshop.dto.UserDto;
import dunn.dunnshop.dto.response.user.MyPageDto;
import dunn.dunnshop.entity.Users;
import dunn.dunnshop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users")
    public List<UserDto> getUserForm() {
        return userService.getAllUsers();
    }

    @PostMapping("/user")
    public UserDto userForm(@RequestBody UserDto userDto) {
        return userService.saveOneUser(userDto);
    }

    @ResponseBody
    @PostMapping("/users")
    public List<Users> userListForm(@RequestBody List<Users> usersList) {
        return userService.saveListUser(usersList);
    }

    @GetMapping("/user/{id}")
    public MyPageDto userMyPage(@PathVariable("id") Long id) {
        return userService.mypageForm(id);
    }

    @GetMapping("/signup")
    public String duplicateId(@RequestParam String id) {
        boolean idBoolean = userService.duplicateId(id);
        if (idBoolean == true) {
            return "중복 아이디가 있습니다";
        } else {
            return "중복 아이디가 없습니다";
        }
    }
}
