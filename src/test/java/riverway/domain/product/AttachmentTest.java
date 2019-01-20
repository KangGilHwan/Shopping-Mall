package riverway.domain.product;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AttachmentTest {

    @Test
    public void convert() {
        String file = "자바의 정석.txt";
        assertThat(Attachment.convert(file).length()).isEqualTo(32 + ".txt".length());
    }

    @Test
    public void parseExtention() {
        String file = "DDD Start.jpg";
        assertThat(Attachment.parseExtention(file)).isEqualTo(".jpg");
    }
}