package dunn.dunnshop.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    private MimeMessage createEmail(String sender, String toEmail, String title, String text) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

        helper.setFrom(sender);
        helper.setTo(toEmail);
        helper.setSubject(title);
        helper.setText(text);

        return message;
    }
    public void sendEmail(String sender, String toEmail, String title, String text) throws MessagingException {
        MimeMessage email = createEmail(sender,toEmail, title, text);
        try {
            javaMailSender.send(email);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
