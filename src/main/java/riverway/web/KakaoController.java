package riverway.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import riverway.domain.SocialCode;
import riverway.domain.User;
import riverway.dto.KakaoDto;
import riverway.dto.KakaoPaymentDto;
import riverway.dto.PaymentDto;
import riverway.dto.UserDto;
import riverway.service.PaymentService;
import riverway.service.UserService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/api/kakao")
public class KakaoController {

    private static final Logger log = LoggerFactory.getLogger(KakaoController.class);

    @Value("${kakao.admin.key}")
    private String ADMIN_KEY;

    @Value("${kakao.client.id}")
    private String CLIENT_ID;

    @Value("${kakao.redirect.uri}")
    private String REDIRECT_URI;

    @Value("${kakao.token}")
    private String TOKEN_URI;

    @Value("${kakao.user.info}")
    private String USER_INFO_URI;

    @Value("${kakao.payment.ready}")
    private String PAYMENT_READY_URI;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;


    @GetMapping("/oauth")
    public String kakaoLogin(String code, Model model, HttpSession session) throws Exception {
        log.debug("code : {}", code);
        KakaoDto response = getAccessToken(code);
        JsonNode userInfo = getUserInfo(response.getAccess_token());
        Optional<User> maybeUser = userService.loginKakao(userInfo.get("id").asLong());
        log.debug("UserInfoId : {}", userInfo.get("id"));
        log.debug("KakaoOauthDto : {}", response);
        if (!maybeUser.isPresent()) {
            UserDto userDto = UserDto.build()
                    .setSocialId(userInfo.get("id").asLong())
                    .setSocialCode(SocialCode.KAKAO);
            maybeUser = Optional.of(userService.socialRegister(userDto));
        }
        session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, maybeUser.get());
        return "redirect:/";
    }

    @GetMapping("/payment/{orderId}")
    public String pay(@PathVariable Long orderId) {
        PaymentDto payment = paymentService.getPayment(orderId);
        log.debug("Admin kakao : {}", ADMIN_KEY);
        log.debug("url kakao : {}", PAYMENT_READY_URI);
        HttpEntity<MultiValueMap<String, Object>> request = HtmlFormDataBuilder.urlEncodeFormJSONKakao()
                .setHeader("Authorization", "KakaoAK " + ADMIN_KEY)
                .addParameter("cid", "TC0ONETIME")
                .addParameter("partner_order_id", payment.getOrderId())
                .addParameter("partner_user_id", payment.getUsername())
                .addParameter("item_name", payment.getItemName())
                .addParameter("quantity", payment.getQuantity())
                .addParameter("total_amount", payment.getTotalPrice())
                .addParameter("tax_free_amount", payment.getTotalPrice() / 10)
                .addParameter("approval_url", "http://localhost:8060/api/kakao/success")
                .addParameter("fail_url", "http://localhost:8060/api/kakao/fail")
                .addParameter("cancel_url", "http://localhost:8060/api/kakao/cancel")
                .build();
        log.debug("request to kakao : {}", request);
        KakaoPaymentDto response = restTemplate.postForObject(PAYMENT_READY_URI, request, KakaoPaymentDto.class);
        log.debug("response from kakao : {}", response);
        return "redirect:" + response.getNext_redirect_pc_url();
    }

    private KakaoDto getAccessToken(String code) {
        HttpEntity<MultiValueMap<String, Object>> request = HtmlFormDataBuilder.urlEncodeFormJSON()
                .addParameter("grant_type", "authorization_code")
                .addParameter("client_id", CLIENT_ID)
                .addParameter("redirect_uri", REDIRECT_URI)
                .addParameter("code", code)
                .build();

        KakaoDto response = restTemplate.postForObject(TOKEN_URI, request, KakaoDto.class);
        return response;
    }

    private JsonNode getUserInfo(String accessToken) throws IOException {
        log.debug("accessToken : {}", accessToken);
        HttpEntity<MultiValueMap<String, Object>> request = HtmlFormDataBuilder.urlEncodeFormJSON()
                .setHeader("Authorization", "Bearer " + accessToken)
                .build();

        String response = restTemplate.postForObject(USER_INFO_URI, request, String.class);
        log.debug("request : {}", request);
        log.debug("response : {}", response);

        JsonNode userInfo = mapper.readTree(response);
        log.debug("UserInfo : {}", userInfo);
        return userInfo;
    }
}