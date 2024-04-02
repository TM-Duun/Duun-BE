package dunn.dunnshop.mail;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    // json 으로 넘어온 이메일로 인증번호 보내기
    @PostMapping("/email")
    public String mailSend(@RequestBody @Valid EmailRequestDto emailRequestDto) {
        System.out.println("인증 이메일 : " + emailRequestDto.getEmail());
        return emailService.joinEmail(emailRequestDto.getEmail());
    }

    // 인증번호 확인하기
    @PostMapping("/email/check")
    public String AuthCheck(@RequestBody @Valid EmailCheckDto emailCheckDto) {
        Boolean checked = emailService.CheckAuthNum(emailCheckDto.getEmail(), emailCheckDto.getCode());

        if (checked) {
            return "ok";
        } else {
            throw new NullPointerException("잘못됨!");
        }
    }
}
