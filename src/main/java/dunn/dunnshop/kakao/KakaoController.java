package dunn.dunnshop.kakao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class KakaoController {

    private final KakaoService kakaoService;

    @GetMapping("/login/kakao")
    public String loginForm(Model model) {
        model.addAttribute("kakaoApiKey", kakaoService.getClient_id());
        model.addAttribute("redirectUri", kakaoService.getKakao_redirect_url());

        return "login";
    }

    @RequestMapping("/login/kakao/callback")
    public String kakaoLogin(@RequestParam(value = "code") String code) {
        // 토큰 받기
        KakaoOAuth oAuthToken = kakaoService.getAccessToken(code);
        String accessToken = oAuthToken.getAccess_token();

        // 사용자 정보받기
        KakaoProfile userInfo = kakaoService.getUserInfo(accessToken);

        return "result";
    }
}
