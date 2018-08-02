package riverway.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import riverway.dto.KakaoDto;

import java.util.Arrays;

@Controller
//@RequestMapping("/kakao")
public class KaKaoLoginController {

    private static final Logger log = LoggerFactory.getLogger(KaKaoLoginController.class);
//
//    private final String client_id = "8fb62069a7f7f7a42088372bde732ae2";
//    private final String host = "https://kauth.kakao.com";
//    private final String redirectUrl = "http://localhost:8070/kakao/oauth";
//    private RestTemplate restTemplate = new RestTemplate();
//
//
//    @GetMapping("/oauth")
//    public String getAccessToken2(String code) {
//        log.debug("Here");
//        log.debug("Code!! : {}", code);
//        KakaoDto ka = getAccessToken(code);
//        log.debug("kkakaka : {} ", ka);
//        return "/loginForm";
//    }
//
//    private KakaoDto getAccessToken(String code) {
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML, MediaType.APPLICATION_JSON));
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("grant_type", "authorization_code");
//        params.add("client_id", client_id);
//        params.add("redirect_uri", redirectUrl);
//        params.add("code", code);
//
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
//        KakaoDto response = restTemplate.postForObject("https://kauth.kakao.com/oauth/token",
//                request, KakaoDto.class);
//        return response;
//    }
}
