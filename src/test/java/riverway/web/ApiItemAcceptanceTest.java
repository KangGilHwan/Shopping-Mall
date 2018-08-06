package riverway.web;

import org.junit.Test;
import riverway.dto.ItemDto;
import support.test.AcceptanceTest;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ApiItemAcceptanceTest extends AcceptanceTest {

    @Test
    public void create() {
        ItemDto item = ItemDto.build()
                .setItemname("TeeShirt")
                .setPrice(10000);

        String location = createResource("/api/items", item, basicAuthTemplate());
        ItemDto dbItem = getResource(location, ItemDto.class);
        assertThat(item, is(dbItem));
    }
}
