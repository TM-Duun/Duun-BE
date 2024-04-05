package dunn.dunnshop.service;

import dunn.dunnshop.dto.AuthRequestDto;
import dunn.dunnshop.repository.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MailService mailService;
    @Autowired
    private RedisUtil redisUtil;

    private String createCode() throws NoSuchAlgorithmException {
        int len = 8;
        Random random = SecureRandom.getInstanceStrong();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < len; i++) {
            builder.append(random.nextInt(10));
        }

        return builder.toString();
    }

    public void sendCodeToEmail(String toEmail) throws NoSuchAlgorithmException, MessagingException {
        // 이미 있는 이메일인지 확인
        //this.checkDuplicatedEmail(toEmail);
        String sender = "joo14753@naver.com";
        String title = "DunnShop 회원가입 인증번호 메일입니다.";
        String authCode = this.createCode();

        // 메일 내용 작성
        String content = "";
        content+="인증번호는 [";
        content+=authCode;
        content+="] 입니다.";

        //인증코드 redis에 저장
        redisUtil.setDataExpire(authCode, toEmail, 60*5L);

        mailService.sendEmail(sender,toEmail, title, content);
    }

    public boolean checkCode(AuthRequestDto authRequestDto) {
        //코드 X
        if (redisUtil.getData(authRequestDto.getCode()) == null) {
            return false;
        } else if (redisUtil.getData(authRequestDto.getCode()).equals(authRequestDto.getMail())) {
            //코드 O, 메일 O
            return true;
        } else {
            //코드 O, 메일 X
            return false;
        }
    }



}

