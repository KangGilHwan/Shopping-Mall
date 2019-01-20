package riverway.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import riverway.dto.KakaoPaymentDto;
import riverway.dto.PaymentDto;
import riverway.service.MailService;
import riverway.service.PaymentService;
import riverway.web.support.HtmlFormDataBuilder;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/kakao/payment")
public class KakaoPayController {

    private static final Logger log = LoggerFactory.getLogger(KakaoPayController.class);

    @Value("${kakao.payment.ready}")
    private String PAYMENT_READY_URI;

    @Value("${kakao.payment.approve}")
    private String PAYMENT_APPROVE_URI;

    @Value("${kakao.admin.key}")
    private String ADMIN_KEY;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private MailService mailService;

    @GetMapping("/{orderId}")
    public String pay(@PathVariable Long orderId, HttpSession session) {
        PaymentDto payment = paymentService.getPayment(orderId);
        HttpEntity<MultiValueMap<String, Object>> request = HtmlFormDataBuilder.urlEncodeFormJSONKakao()
                .setHeader("Authorization", "KakaoAK " + ADMIN_KEY)
                .addParameter("cid", "TC0ONETIME")
                .addParameter("partner_order_id", payment.getOrderId())
                .addParameter("partner_user_id", payment.getUsername())
                .addParameter("item_name", payment.getItemName())
                .addParameter("quantity", payment.getQuantity())
                .addParameter("total_amount", payment.getTotalPrice())
                .addParameter("tax_free_amount", payment.getTotalPrice() / 10)
                .addParameter("approval_url", String.format("http://localhost:8060/api/kakao/payment/%d/approve", payment.getOrderId()))
                .addParameter("fail_url", "http://localhost:8060/api/kakao/fail")
                .addParameter("cancel_url", "http://localhost:8060/api/kakao/cancel")
                .build();

        log.debug("request to kakao : {}", request);
        KakaoPaymentDto response = restTemplate.postForObject(PAYMENT_READY_URI, request, KakaoPaymentDto.class);
        session.setAttribute("tid", response.getTid());
        log.debug("response from kakao : {}", response);
        return "redirect:" + response.getNext_redirect_pc_url();
    }

    @GetMapping("/{orderId}/approve")
    public String approve(@PathVariable Long orderId, HttpSession session, String pg_token) {
        PaymentDto payment = paymentService.getPayment(orderId);
        HttpEntity<MultiValueMap<String, Object>> request = HtmlFormDataBuilder.urlEncodeFormJSONKakao()
                .setHeader("Authorization", "KakaoAK " + ADMIN_KEY)
                .addParameter("cid", "TC0ONETIME")
                .addParameter("tid", session.getAttribute("tid"))
                .addParameter("partner_order_id", payment.getOrderId())
                .addParameter("partner_user_id", payment.getUsername())
                .addParameter("pg_token", pg_token)
                .build();
        ResponseEntity<String> response = restTemplate.postForEntity(PAYMENT_APPROVE_URI, request, String.class);
        mailService.send("rlfghksop@naver.com", "결제완료", payment.getItemName());
        log.debug("Success : {}", response);
        return "redirect:/";
    }
}
