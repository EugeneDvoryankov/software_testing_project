import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StringUtilsTest {
    @Test
    void strIsNullOrEmpty() {
        // T1: str is null
        assertThat(StringUtils.substringsBetween(null, "a", "b"))
                .isEqualTo(null);

        // T2: str is empty
        assertThat(StringUtils.substringsBetween("", "a", "b"))
                .isEqualTo(new String[]{});
    }

    @Test
    void openIsNullOrEmpty(){
        // T3: open is null
        assertThat(StringUtils.substringsBetween("abcd", null, "d")).isEqualTo(null);
        // T4: open is empty
        assertThat(StringUtils.substringsBetween("abcd", "", "d")).isEqualTo(null);
    }

    @Test
    void closeIsNullOrEmpty(){
        // T5: close is null
        assertThat(StringUtils.substringsBetween("abcd", "a", null)).isEqualTo(null);
        // T6: close is empty
        assertThat(StringUtils.substringsBetween("abcd", "a", "")).isEqualTo(null);
    }

    @Test
    void strOfLength1(){
        // T7: The single character in str matches the open tag.
        assertThat(StringUtils.substringsBetween("a","a", "b")).isEqualTo(null);
        // T8: The single character in str matches the close tag.
        assertThat(StringUtils.substringsBetween("a","b", "a")).isEqualTo(null);
        // T9: The single character in str does not match either the open or the close tag.
        assertThat(StringUtils.substringsBetween("a","b", "b")).isEqualTo(null);
        // T10: The single character in str matches both the open and close tags.
        assertThat(StringUtils.substringsBetween("a","a", "a")).isEqualTo(null);
    }

    @Test
    void openAndCloseOfLength1() {
        // T11: str does not contain either the open or the close tag.
        assertThat(StringUtils.substringsBetween("xyz", "a", "b")).isEqualTo(null);
        // T12: str contains the open tag but does not contain the close tag.
        assertThat(StringUtils.substringsBetween("xyz", "x", "a")).isEqualTo(null);
        // T13: str contains the close tag but does not contain the open tag.
        assertThat(StringUtils.substringsBetween("xyz", "a", "z")).isEqualTo(null);
        // T14: str contains both the open and close tags.
        assertThat(StringUtils.substringsBetween("xyz", "x", "z")).isEqualTo(new String[]{"y"});
        // T15: str contains both the open and close tags multiple times.
        assertThat(StringUtils.substringsBetween("xyzxyz", "x", "z")).isEqualTo(new String[]{"y", "y"});
        // Extra case
        assertThat(StringUtils.substringsBetween("abcabyt byrc", "a", "c"))
                .isEqualTo(new String[]{"b", "byt byr"});
    }

    @Test
    void openAndCloseTagsOfDifferentSize(){
        // T16: str does not contain either the open or the close tag.
        assertThat(StringUtils.substringsBetween("aabcc", "xx", "yy")).isEqualTo(null);
        // T17: str contains the open tag but does not contain the close tag.
        assertThat(StringUtils.substringsBetween("aabcc", "aa", "yy")).isEqualTo(null);
        // T18: str contains the close tag but does not contain the open tag.
        assertThat(StringUtils.substringsBetween("aabcc", "xx", "cc")).isEqualTo(null);
        // T19: str contains both the open and close tags.
        assertThat(StringUtils.substringsBetween("aabbcc","aa","cc"))
                .isEqualTo(new String[]{"bb"});
        // T20: str contains both the open and close tags multiple times.
        assertThat(StringUtils.substringsBetween("aabbccaaeecc","aa","cc"))
                .isEqualTo(new String[]{"bb","ee"});
        // extra case
        assertThat(StringUtils.substringsBetween("a abb ddc ca abbcc","a a", "c c"))
                .isEqualTo(new String[] {"bb dd"});
    }

    @Test
    void noSubstringBetweenOpenAndCloseTags(){
        // T21: str contains both the open and close tags with no characters between
        //them.
        assertThat(StringUtils.substringsBetween("aabb", "aa", "bb"))
                .isEqualTo(new String[] {""});
    }
}
