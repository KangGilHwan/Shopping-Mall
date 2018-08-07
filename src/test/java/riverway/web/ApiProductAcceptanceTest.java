package riverway.web;

import org.junit.Test;
import riverway.dto.ProductDto;
import support.test.AcceptanceTest;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ApiProductAcceptanceTest extends AcceptanceTest {

    @Test
    public void create() {
        ProductDto product = ProductDto.build()
                .setName("TeeShirt")
                .setPrice(10000)
                .setDescription("티셔츠 입니다");


        String location = createResource("/api/products", product, basicAuthTemplate());
        ProductDto dbProduct = getResource(location, ProductDto.class);
        assertThat(product, is(dbProduct));
    }
}
