package dunn.dunnshop.mail;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class EmailRequestDto {
    @Email
    @NotEmpty(message = "이메일을 입력해주세요!")
    private String email;
}
