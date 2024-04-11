package dunn.dunnshop.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {
    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private boolean auth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private boolean starttlsEnable;

    @Value("${spring.mail.properties.mail.smtp.starttls.required}")
    private boolean starttlsRequired;

    @Value("${spring.mail.properties.mail.smtp.ssl.trust}")
    private String sslTrust;

    @Value("${spring.mail.properties.mail.smtp.ssl.protocols}")
    private String sslProtocols;

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        mailSender.setDefaultEncoding("UTF-8");
        mailSender.setJavaMailProperties(getMailProperties());

        return mailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", auth);
        properties.put("mail.smtp.starttls.enable", starttlsEnable);
        properties.put("mail.smtp.starttls.required", starttlsRequired);
//        properties.put("mail.smtp.ssl.trust", sslTrust);
        properties.put("mail.smtp.ssl.protocols", sslProtocols);

        return properties;
    }
//    @Bean
//    public JavaMailSender mailSender() {
//        // JavaMailSender 의 구현체 생성
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//
//        mailSender.setHost("smtp.gmail.com");           // 이메일을 전송하는데에 SMTP 서버 호스트 설정
//        mailSender.setPort(587);                        // 587번으로 포트번호 지정
//        mailSender.setUsername("kimseonhee126@gmail.com");  // 보내는 사람 구글 계정 작성
//        mailSender.setPassword("mahf vmac vbqb babz");      // 구글 앱 비밀번호 넣기
//
//        // JavaMail 의 속성을 설정하기 위해 Properties 객체 생성
//        Properties javaMailProperties = new Properties();
//        javaMailProperties.put("mail.transport.protocol", "smtp");          // 프로토콜(protocol) smtp 사용
//        javaMailProperties.put("mail.smtp.auth", "true");                   // smtp 서버에 인증
//        javaMailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");     // SSL 소켓 팩토리 클래스 사용
//        javaMailProperties.put("mail.smtp.starttls.enable", "true");        // STARTTLS(TLS를 시작하는 명령)를 사용하여 통신 암호화
//        javaMailProperties.put("mail.smtp.ssl.trust", "smtp.naver.com");    // smtp 서버의 ssl 인증서 신뢰
//        javaMailProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");       // 사용할 ssl 프로토콜 버전
//        /*
//        * TLSv1.2 -> JDK 버전에 맞춰서 사용해야 함. 안그러면 오류 엄청 난다.
//        * */
//
//        // properties 를 mailSender 에 넣는다
//        mailSender.setJavaMailProperties(javaMailProperties);
//
//        // 빈으로 등록
//        return mailSender;
//    }
}
