package riverway.web;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import riverway.dto.ProductDto;
import support.test.AcceptanceTest;
import support.test.HtmlFormDataBuilder;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ApiProductAcceptanceTest extends AcceptanceTest {

    private static final Logger log = LoggerFactory.getLogger(ApiProductAcceptanceTest.class);
//    @Test
//    public void create() {
//        ProductDto product = ProductDto.build()
//                .setName("TeeShirt")
//                .setPrice(10000)
//                .setDescription("티셔츠 입니다");
//        MultipartFile multipartFile = new CommonsMultipartFile();
//
//
//        String location = createResource("/api/products", product, basicAuthTemplate());
//        ProductDto dbProduct = getResource(location, ProductDto.class);
//        assertThat(product, is(dbProduct));
//    }
}
