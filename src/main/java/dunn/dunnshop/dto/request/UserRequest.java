package dunn.dunnshop.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserRequest {
    String userId;
    String password;
    String name;
    String phone;
    String email;
    String address;

    @Builder
    public UserRequest(String userId, String password, String name,
                       String phone, String email, String address) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
}
