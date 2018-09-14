package riverway.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import riverway.dto.OrderCouponDto;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class ApiOrderController {

    private static final Logger log = LoggerFactory.getLogger(ApiOrderController.class);

    @PostMapping("")
    public ResponseEntity<Void> order(@RequestBody List<OrderCouponDto> order){

        log.debug("order : {}", order);
        URI uri = URI.create("/order" + 1);
        return ResponseEntity.created(uri).build();
    }
}
