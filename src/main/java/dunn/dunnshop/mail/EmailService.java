package dunn.dunnshop.mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private RedisUtil redisUtil;

    private int authNumber;

    // 6자리로 이루어진 무작위의 수 반환
    public void makeRandomNumber() {
        Random rnd = new Random();
        String randomNumber = "";

        for (int i = 0; i < 6; i++) {
            randomNumber += Integer.toString(rnd.nextInt(10));
        }

        authNumber = Integer.parseInt(randomNumber);
    }

    // 메일을 어디서(setFrom), 어디로(toEmail), 인증번호를 어떤 형식(html)로 보내는지 작성
    public String joinEmail(String email) {
        makeRandomNumber();

        String setFrom = "kimseonhee126@naver.com";         // 보내는 사람 이메일 작성
        String toEmail = email;                             // 받는 사람 이메일
        String title = "DunnShop 회원가입 인증번호 메일입니다.";     // 메일 제목 작성
        String content = "인증 번호는 " + authNumber + " 입니다!";  // 메일 내용 작성(html 형식으로 작성)

        mailSend(setFrom, toEmail, title, content);
        return Integer.toString(authNumber);
    }

    // 메일 전송하기
    public void mailSend(String setFrom, String toEmail, String title, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            // 메시지와 관련된 설정 작성
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

            // 이메일을 보내는 사람 주소 설정
            helper.setFrom(setFrom);

            // 이메일을 받는 사람 주소 설정
            helper.setTo(toEmail);

            // 메일 제목 작성
            helper.setSubject(title);

            // 메일 내용 설정, html 로 작성했기 때문에 true 로 설정해야함
            helper.setText(content, true);
            javaMailSender.send(message);

            // 이메일 서버에 연결할 수 없음 or 잘못된 이메일 주소 or 인증오류 발생
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        // 5분동안 인증번호가 유효하게 -> 5분이 지나면 인증번호 유효하지 않음!
        redisUtil.setDataExpire(Integer.toString(authNumber), toEmail, 60*5L);
    }

    // 인증번호 확인 -> 사용자가 입력한 인증번호와 실제 인증번호 비교
    public boolean CheckAuthNum(String email, String code) {
        if (redisUtil.getData(code) == null) {
            return false;
        } else if (redisUtil.getData(code).equals(email)) {
            return true;
        } else {
            return false;
        }
    }
}
