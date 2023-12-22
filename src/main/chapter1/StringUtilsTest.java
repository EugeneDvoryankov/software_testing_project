import org.junit.jupiter.api.Test;
import static org.apache.commons.lang.StringUtils.substringsBetween;
import static org.assertj.core.api.Assertions.assertThat;

public class StringUtilsTest {
    @Test
    void strIsNullOrEmpty() {
        assertThat(substringsBetween(null, "a", "b"))
                .isEqualTo(null);
        assertThat(substringsBetween("", "a", "b"))
                .isEqualTo(new String[]{});
    }
}
