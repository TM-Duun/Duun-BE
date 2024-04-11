package dunn.dunnshop.kakao;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class KakaoProfile {

    private String id;
    private LocalDateTime updatedAt;
    private String nickname;
    private String profileImage;

    public KakaoProfile(String jsonResponseBody) {

        Gson gson = new Gson();
        JsonElement element = gson.fromJson(jsonResponseBody, JsonElement.class);

        // kakao 에서 저장되어 있는 고유 id
        JsonObject asJsonObject = element.getAsJsonObject();
        this.id = element.getAsJsonObject().get("id").getAsString();

        // kakao 에서 가져온 json 결과물
        // {"id":3431833814,"connected_at":"2024-04-11T04:33:28Z",
        // "properties":{"nickname":"김선희","profile_image":"http://k.kakaocdn.net/dn/ijMh2/btsFdXouKde/k6bbJhxGo154F3rmnDpyO0/img_640x640.jpg","thumbnail_image":"http://k.kakaocdn.net/dn/ijMh2/btsFdXouKde/k6bbJhxGo154F3rmnDpyO0/img_110x110.jpg"},
        // "kakao_account":{"profile_nickname_needs_agreement":false,"profile_image_needs_agreement":false,
        // "profile":{"nickname":"김선희","thumbnail_image_url":"http://k.kakaocdn.net/dn/ijMh2/btsFdXouKde/k6bbJhxGo154F3rmnDpyO0/img_110x110.jpg","profile_image_url":"http://k.kakaocdn.net/dn/ijMh2/btsFdXouKde/k6bbJhxGo154F3rmnDpyO0/img_640x640.jpg","is_default_image":false,"is_default_nickname":false}}}

        // kakao 에서 updatedAt 정보 가져오기
        String connected_at = element.getAsJsonObject().get("connected_at").getAsString();
        connected_at = connected_at.substring(0, connected_at.length() - 1);
        this.updatedAt = LocalDateTime.parse(connected_at, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));

        // nickname, profile_image 정보 가져오기
        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
        this.nickname = properties.getAsJsonObject().get("nickname").getAsString();
        this.profileImage = properties.getAsJsonObject().get("profile_image").getAsString();

        System.out.println("id = " + id);
        System.out.println("updatedAt = " + updatedAt);
        System.out.println("nickname = " + nickname);
        System.out.println("profileImage = " + profileImage);
    }
}
