package dunn.dunnshop.controller;

import dunn.dunnshop.domain.User;
import dunn.dunnshop.dto.UserDto;
import dunn.dunnshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController  // @Controller + @ResponseBody
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/save")
    public void save(@RequestBody User user){
        userService.save(user);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id){
        userService.remove(id);
    }

    @GetMapping("/{id}")
    public UserDto findUser(@PathVariable("id") Long id){
        return userService.findUser(id);
    }

    @GetMapping()
    public List<UserDto> findAllUser(){
        return userService.findAllUser();
    }

}
