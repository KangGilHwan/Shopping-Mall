package riverway.web;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import riverway.domain.cart.Option;
import riverway.domain.cart.Size;
import riverway.dto.ProductDto;
import support.test.AcceptanceTest;
import support.test.HtmlFormDataBuilder;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class ApiCartAcceptanceTest extends AcceptanceTest{

    private static final Logger log = LoggerFactory.getLogger(ApiCartAcceptanceTest.class);

    @Test
    public void addCart_no_login(){
        HttpEntity<MultiValueMap<String, Object>> request = HtmlFormDataBuilder.multipartFormData()
                .addParameter("name", "Shoes")
                .addParameter("price", 20000)
                .addParameter("description", "신발 입니다")
                .addParameter("image", new ClassPathResource("logback.xml"))
                .build();

        String location = createResource("/api/products", request, basicAuthTemplateSeller());
        Option option = new Option(Size.S, 2);
        ResponseEntity<String> response = template.postForEntity(location + "/cart", option, String.class);
        log.debug("Response : {}", response);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertTrue(response.getBody().contains("Shoes"));
        assertTrue(response.getBody().contains("신발 입니다"));
    }

    @Test
    public void addCart_login(){
        HttpEntity<MultiValueMap<String, Object>> request = HtmlFormDataBuilder.multipartFormData()
                .addParameter("name", "Hat")
                .addParameter("price", 5000)
                .addParameter("description", "모자 입니다")
                .addParameter("image", new ClassPathResource("logback.xml"))
                .build();

        String location = createResource("/api/products", request, basicAuthTemplateSeller());
        Option option = new Option(Size.L, 3);
        ResponseEntity<String> response = basicAuthTemplateConsumer().postForEntity(location + "/cart", option, String.class);
        log.debug("Response : {}", response);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertTrue(response.getBody().contains("Hat"));
        assertTrue(response.getBody().contains("모자 입니다"));
    }
}
