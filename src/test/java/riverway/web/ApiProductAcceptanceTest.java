package riverway.web;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import riverway.dto.ProductDto;
import support.test.AcceptanceTest;
import support.test.HtmlFormDataBuilder;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ApiProductAcceptanceTest extends AcceptanceTest {

    private static final Logger log = LoggerFactory.getLogger(ApiProductAcceptanceTest.class);

    @Test
    public void create_consumer() {
        HttpEntity<MultiValueMap<String, Object>> request = HtmlFormDataBuilder.multipartFormData()
                .addParameter("name", "TeeShirt")
                .addParameter("price", 10000)
                .addParameter("description", "티셔츠 입니다")
                .addParameter("image", new ClassPathResource("logback.xml"))
                .build();

        ResponseEntity<String> response = basicAuthTemplateConsumer().postForEntity("/api/products", request, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.FORBIDDEN));
    }

    @Test
    public void create_seller() {
        HttpEntity<MultiValueMap<String, Object>> request = HtmlFormDataBuilder.multipartFormData()
                .addParameter("name", "TeeShirt")
                .addParameter("price", 10000)
                .addParameter("description", "티셔츠 입니다")
                .addParameter("image", new ClassPathResource("logback.xml"))
                .build();

        String location = createResource("/api/products", request, basicAuthTemplateSeller());
        ProductDto dbProductDto = getResource(location, ProductDto.class);
        log.debug("Response : {}", dbProductDto);
    }
}
