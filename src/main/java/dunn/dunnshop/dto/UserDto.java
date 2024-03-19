package dunn.dunnshop.dto;

import lombok.Getter;

@Getter
public class UserDto {

    private String userId;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String address;


    public UserDto(String userId, String password, String name, String email, String address) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.address = address;
    }
}
