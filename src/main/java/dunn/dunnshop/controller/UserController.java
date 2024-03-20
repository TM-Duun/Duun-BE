package dunn.dunnshop.controller;

import dunn.dunnshop.entity.Users;
import dunn.dunnshop.dto.UserDto;
import dunn.dunnshop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/user/all")
    public List<UserDto> getUserForm() {
        return userService.getAllUsers();
    }

    @ResponseBody
    @PostMapping("/user/new")
    public Users userForm(@RequestBody Users users) {
        Users savedUser = userService.saveUser(users);

        log.info("userDto={}", users);
        log.info("name={}, email={}", users.getName(), users.getEmail());

        return users;
    }
}
