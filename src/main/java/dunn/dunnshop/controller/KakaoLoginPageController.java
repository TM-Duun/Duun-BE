package dunn.dunnshop.controller;

import dunn.dunnshop.service.KakaoApi;
import dunn.dunnshop.service.KakaoProfile;
import dunn.dunnshop.service.OAuthToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class KakaoLoginPageController {

    private final KakaoApi kakaoApi;

    @GetMapping("")
    public String loginPage(Model model) {
        model.addAttribute("kakaoApiKey", kakaoApi.getClientId());
        model.addAttribute("redirectUri", kakaoApi.getRedirectUri());
        return "login";

    }

    @RequestMapping("/kakao")
    public String kakaoLogin(@RequestParam(value="code")String code){
        //@RequestParam(value="code")String code 인가 코드 받기

        // 인가 코드를 통해서 토큰 받기
        OAuthToken oAuthToken = kakaoApi.getAccessToken(code);
        String accessToken = oAuthToken.getAccess_token();

        //토큰으로 사용자 정보 조회하기
        KakaoProfile userInfo = kakaoApi.getUserInfo(accessToken);

//        String email = (String)userInfo.getEmail();
        String nickname = (String)userInfo.getNickname();

//        System.out.println("email = " + email);
        System.out.println("nickname = " + nickname);
        System.out.println("accessToken = " + accessToken);

        return "success";
    }

}