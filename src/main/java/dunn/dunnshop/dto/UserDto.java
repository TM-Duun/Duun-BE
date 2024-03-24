package dunn.dunnshop.dto;

import dunn.dunnshop.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDto {
    private String userId;
    private String userName;
    private String phoneNumber;
//    private String email;
//    private String address;
//    private String level;
//    private Long point;

    @Builder
    public UserDto(String userId, String userName, String phoneNumber) {
        this.userId = userId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
//        this.email = email;
//        this.address = address;
//        this.level = level;
//        this.point = point;
    }

    public static UserDto from(User user){
        return UserDto.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .phoneNumber(user.getPhoneNumber())
//                .email(user.getEmail())
//                .address(user.getAddress())
//                .level(user.getLevel())
//                .point(user.getPoint())
                .build();
    }
}
