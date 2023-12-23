import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang.ArrayUtils.EMPTY_STRING_ARRAY;
import static org.apache.commons.lang.StringUtils.isEmpty;
import static org.assertj.core.api.Assertions.assertThat;

public class StringUtils {

    @Test
    void simpleCase(){
        assertThat(
                StringUtils.substringsBetween("abcd", "a", "d"))
                .isEqualTo(new String[] { "bc" });
    }

    @Test
    void manySubstrings(){
        assertThat(
                StringUtils.substringsBetween("abcdabcd", "a", "d")
        ).isEqualTo(new String[] {"bc", "bc"});
    }

    @Test
    void openAndCloseTagsThatAreLongerThan1Char() {
        assertThat(StringUtils.substringsBetween("aabcddaabfdd", "aa","dd"))
                .isEqualTo(new String[] {"bc", "bf"});
    }

    @Test
    void nullStr(){
        assertThat(StringUtils.substringsBetween(null, "a", "d")).isEqualTo(null);
    }

    @Test
    void nullOpen(){
        assertThat(
                StringUtils.substringsBetween("abcd", null, "d"))
                .isEqualTo(null);
    }

    @Test
    void nullClose(){
        assertThat(
                StringUtils.substringsBetween("abcd", "a", null)
        ).isEqualTo(null);
    }

    @Test
    void emptyStr(){
        assertThat(
                StringUtils.substringsBetween("", "a", "d")
        ).isEqualTo(EMPTY_STRING_ARRAY);
    }

    @Test
    void emptyOpen(){
        assertThat(
                StringUtils.substringsBetween("abcd", "", "d")
        ).isEqualTo(null);
    }

    @Test
    void emptyClose(){
        assertThat(
                StringUtils.substringsBetween("abcd", "a", "")
        ).isEqualTo(null);
    }

    public static String[] substringsBetween(final String str, final String open, final String close){
        // if the pre-conditions do not hold, returns null right away
        if (str == null || isEmpty(open) || isEmpty(close)){
            return null;
        }

        // if the string is empty, returns an empty array immediat
        int strLen = str.length();
        if(strLen == 0){
            return EMPTY_STRING_ARRAY;
        }

        int closeLen = close.length();
        int openLen = open.length();
        List<String> list = new ArrayList<>();
        int pos = 0;

        while (pos < strLen - closeLen){
            // Looks for the next occurrence of the open tag
            int start = str.indexOf(open, pos);

            // Breaks the loop if the open tag does not appear again in the string
            if (start < 0){
                break;
            }

            start += openLen;
            int end = str.indexOf(close, start);
            if(end < 0){
                break;
            }

            list.add(str.substring(start, end));
            pos = end + closeLen;

        }

        if (list.isEmpty()){
            return null;
        }

        return list.toArray(EMPTY_STRING_ARRAY);
    }
}
