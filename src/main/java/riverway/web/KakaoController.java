package riverway.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.manager.util.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import riverway.domain.SocialCode;
import riverway.domain.User;
import riverway.dto.KakaoDto;
import riverway.service.UserService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Controller
@RequestMapping("/api/kakao")
public class KakaoController {
//
    @Autowired
    private UserService userService;

    private final String CLIENT_ID = "e28a0bfdf53c249ef1f40e3786e62e5b";
    private static final Logger log = LoggerFactory.getLogger(KakaoController.class);
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/oauth")
    public String accessCode(String code, Model model, HttpSession session) throws Exception {
        log.debug("code : {}", code);
        KakaoDto response = getAccessToken(code);
        JsonNode userInfo = getUserInfo(response.getAccess_token());
        log.debug("UserInfoId : {}", userInfo.get("id"));
        Optional<User> maybeUser = userService.loginKakao(userInfo.get("id").asLong());
        log.debug("KakaoOauthDto : {}", response);
        if (!maybeUser.isPresent()){
            model.addAttribute("socialId", userInfo.get("id").asText());
            model.addAttribute("username", userInfo.get("properties").get("nickname").asText());
            model.addAttribute("profileImage", userInfo.get("properties").get("profile_image").asText());
            model.addAttribute("socialCode", SocialCode.KAKAO);
            log.debug("nickname : {}", userInfo.get("properties").get("nickname").asText());
//            model.addAttribute("")
            return "/user/form";
        }
        session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, maybeUser.get());
        return "redirect:/";
    }

    private KakaoDto getAccessToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML, MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", CLIENT_ID);
        params.add("redirect_uri", "http://localhost:8060/api/kakao/oauth");
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        KakaoDto response = restTemplate.postForObject("https://kauth.kakao.com/oauth/token",
                request, KakaoDto.class);
        return response;
    }

    private JsonNode getUserInfo(String accessToken) throws IOException {
        log.debug("accessToken : {}", accessToken);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML, MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(params, headers);
        log.debug("request : {}", request);
        String response = restTemplate.postForObject("https://kapi.kakao.com/v2/user/me", request, String.class);
        log.debug("response : {}", response);

        JsonNode userInfo = null;
        ObjectMapper mapper = new ObjectMapper();
        userInfo = mapper.readTree(response);
        log.debug("UserInfo : {}", userInfo);
        return userInfo;
    }
}