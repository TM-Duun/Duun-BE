package dunn.dunnshop.kakao;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@Getter
@Component
public class KakaoService {

    @Value("${kakao.client.id}")
    private String client_id;

    @Value("${kakao.redirect.url}")
    private String kakao_redirect_url;

    public KakaoOAuth getAccessToken(String code) {
        String reqUrl = "https://kauth.kakao.com/oauth/token";

        RestTemplate rt = new RestTemplate();

        // HttpHeader 오브젝트
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpBody 오브젝트
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id",client_id);
        params.add("redirect_uri", kakao_redirect_url);
        params.add("code", code);

        // http 바디(params)와 http 헤더(headers)를 가진 엔티티
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        // reqUrl로 Http 요청 , POST 방식
        ResponseEntity<String> response =
                rt.exchange(reqUrl, HttpMethod.POST, kakaoTokenRequest, String.class);

        String responseBody = response.getBody();

        Gson gson = new Gson();
        KakaoOAuth kakaoOAuth = gson.fromJson(responseBody, KakaoOAuth.class);

        return kakaoOAuth;
    }

    public KakaoProfile getUserInfo(String accessToken) {
        String reqUrl = "https://kapi.kakao.com/v2/user/me";

        RestTemplate rt = new RestTemplate();

        //HttpHeader 오브젝트
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //http 헤더(headers)를 가진 엔티티
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest =
                new HttpEntity<>(headers);

        //reqUrl로 Http 요청 , POST 방식
        ResponseEntity<String> response =
                rt.exchange(reqUrl, HttpMethod.POST, kakaoProfileRequest, String.class);

        KakaoProfile kakaoProfile = new KakaoProfile(response.getBody());

        return kakaoProfile;
    }
}
