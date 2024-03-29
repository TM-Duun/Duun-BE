package dunn.dunnshop.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponse {

    private String userId;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String address;

    @Builder
    public UserResponse(String userId, String password, String phone, String name, String email, String address) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
}
