import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class DumbTest {
    @Test
    void zero() {
        assertThat(0, is(0));
    }
}
