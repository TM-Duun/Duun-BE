package dunn.dunnshop.service;

import dunn.dunnshop.dto.AuthRequestDto;
import dunn.dunnshop.repository.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {
    private static final int EMAIL_CODE_LEN = 8;
    private static final int BOUNDARY = 10;


    private final MailService mailService;

    @Value("${smtp.sender}")
    private String sender;
    @Autowired
    private RedisUtil redisUtil;

    private String createCode() throws NoSuchAlgorithmException {
        Random random = SecureRandom.getInstanceStrong();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < EMAIL_CODE_LEN; i++) {
            builder.append(random.nextInt(BOUNDARY));
        }

        return builder.toString();
    }

    public void sendCodeToEmail(String toEmail) throws NoSuchAlgorithmException, MessagingException {
        // 이미 있는 이메일인지 확인
        //this.checkDuplicatedEmail(toEmail);

        String sender = this.sender;

        // 메일 제목
        String title = "DunnShop 회원가입 인증번호 메일입니다.";

        // 메일 내용
        String authCode = this.createCode();
        String format = "인증번호는 [ %s ] 입니다.";
        final String content= String.format(format, authCode);

        //인증코드 redis에 저장
        redisUtil.setDataExpire(authCode, toEmail, 60*5L);
        mailService.sendEmail(sender,toEmail, title, content);
    }

    public boolean checkCode(AuthRequestDto authRequestDto) {
        if (isCodeAndEmailInRedis(authRequestDto.getCode(),authRequestDto.getMail())) {
            return true;
        }
        return false;
    }

    private boolean isCodeAndEmailInRedis(String code, String email) {
        return redisUtil.hasCode(code) && redisUtil.hasEmail(code, email);


    }

}

