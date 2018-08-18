package riverway.domain;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class RoleTest {

    @Test
    public void hasAdmin_consumer(){
        assertFalse(Role.hasAdmin(Role.CONSUMER));
    }

    @Test
    public void hasAdmin_seller(){
        assertTrue(Role.hasAdmin(Role.SELLER));
    }
}
