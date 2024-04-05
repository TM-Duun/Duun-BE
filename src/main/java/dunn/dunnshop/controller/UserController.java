package dunn.dunnshop.controller;

import dunn.dunnshop.domain.User;
import dunn.dunnshop.dto.AuthRequestDto;
import dunn.dunnshop.dto.UserDto;
import dunn.dunnshop.service.AuthService;
import dunn.dunnshop.service.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;


@RestController  // @Controller + @ResponseBody
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthService authService;

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

    // 이메일 인증
    @PostMapping("/auth-mail/{email}")
    public void sendCodeToEmail(@PathVariable("email") String toEmail) throws NoSuchAlgorithmException, MessagingException {
        authService.sendCodeToEmail(toEmail);
    }

    @GetMapping("/auth-mail")
    public String checkCode(@RequestBody AuthRequestDto authRequestDto){
        boolean result=authService.checkCode(authRequestDto);
        if(result) {
            return "인증에 성공하셨습니다!";
        }else{
            return "인증번호가 맞지 않습니다. 다시 한번 확인해주세요.";
        }
    }

}
