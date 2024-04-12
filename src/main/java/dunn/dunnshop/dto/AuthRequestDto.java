package dunn.dunnshop.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthRequestDto {
    private String mail;
    private String code;

    @Builder
    private AuthRequestDto(String mail, String code) {
        this.mail = mail;
        this.code = code;
    }
}
