package dunn.dunnshop.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.google.gson.JsonParser;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class KakaoProfile {
    private Integer id;
    private LocalDateTime connectedAt;
//    private String email;
    private String nickname;

    public KakaoProfile(String jsonResponseBody){
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(jsonResponseBody);

        this.id = element.getAsJsonObject().get("id").getAsInt();

        String connectedAt = element.getAsJsonObject().get("connected_at").getAsString();
        this.connectedAt = parseConnectedDate(connectedAt);

        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
        this.nickname = properties.getAsJsonObject().get("nickname").getAsString();

//        JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
//        this.email = kakaoAccount.getAsJsonObject().get("email").getAsString();

    }

    private LocalDateTime parseConnectedDate(String connectedAt) {

        String result = connectedAt.substring(0, connectedAt.length() - 1);

        return LocalDateTime.parse(result, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));

    }


}
