package dunn.dunnshop.service;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@Slf4j
@Getter
@Component
@ConfigurationProperties(prefix = "kakao")
@Setter
public class KakaoApi {

    private String clientId;
    private String redirectUri;

    public OAuthToken getAccessToken(String code) {
        String reqUrl = "https://kauth.kakao.com/oauth/token";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);

        //RestClient 헤더 추가
        RestClient restClient = RestClient.builder()
                        .baseUrl(reqUrl)
                        .defaultHeaders(
                                httpHeaders -> {
                                    httpHeaders.set(CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");
                                })
                        .build();

        ResponseEntity<String> response = restClient.post()
                .body(params)
                .retrieve()
                .toEntity(String.class);

        String responseBody = response.getBody();

        //JSON Object -> JAVA Object
        Gson gson = new Gson();
        OAuthToken oAuthToken = gson.fromJson(responseBody, OAuthToken.class);

        return oAuthToken;
    }

    public KakaoProfile getUserInfo(String accessToken) {
        String reqUrl = "https://kapi.kakao.com/v2/user/me";

        RestClient restClient = RestClient.builder()
                .baseUrl(reqUrl)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.set("Authorization", "Bearer " + accessToken);
                    httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
                })
                .build();

        ResponseEntity<String> response = restClient.post()
                .retrieve()
                .toEntity(String.class);

        KakaoProfile kakaoProfile = new KakaoProfile(response.getBody());

        return kakaoProfile;
    }

}
