import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.shadow.com.univocity.parsers.common.ArgumentUtils.EMPTY_STRING_ARRAY;
import static sun.util.locale.LocaleUtils.isEmpty;

public class SubString {
    public static String[] substringBetween(final String str, final String open, final String close){
        if(str == null || isEmpty(open) || isEmpty(close)) {
            return null;
        }

        int strLen = str.length();
        if(strLen == 0){
            return EMPTY_STRING_ARRAY;
        }

        int closeLen = close.length();
        int openLen = open.length();
        List<String> list = new ArrayList<>();
        int pos = 0;

        while (pos < strLen - closeLen) {
            int start = str.indexOf(open, pos);
            if (start < 0) {
                break;
            }
            start += openLen;
            int end = str.indexOf(close, start);
            if (end < 0) {
                break;
            }
            list.add(str.substring(start, end));
            pos = end + closeLen;
        }
        if (list.isEmpty()) {
            return null;
        }
        return list.toArray(EMPTY_STRING_ARRAY);
    }

    // Test case based on my intuition
    @Test
    void simpleCase() {
        assertThat(
                StringUtils.substringsBetween("abcd", "a", "d")
        ).isEqualTo(new String[] { "bc" });
    }

    @Test
    void manySubstrings(){
        assertThat(
                StringUtils.substringsBetween("abcdabcdab", "a", "d")
        ).isEqualTo(new String[] {"bc", "bc"});
    }

    @Test
    void openAndCloseTagsThatAreLongerThan1Char() {
        assertThat(
                StringUtils.substringsBetween("aabcddaabfddaab", "aa", "dd")
        ).isEqualTo(new String[] { "bc", "bf" });
    }
}
