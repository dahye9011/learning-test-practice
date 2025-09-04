package jpabook.unittestpractice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringTest {
    @Test
    @DisplayName("substring은 endIndex -1까지 반환한다.")
    void substringTest() {
        // given
        String str = "dahye";

        // when
        String result = str.substring(0, 3);

        // then
        assertEquals("dah", result);
    }

    @Test
    @DisplayName("substring의 endIndex가 문자열 길이와 같으면 끝까지 반환한다.")
    void substring_경계값() {
        String str = "dahye";
        String result = str.substring(0, 5);
        assertEquals("dahye", result);
    }

    @Test
    @DisplayName("substring의 startIndex와 endIndex가 같으면 빈 문자열을 반환한다.")
    void substring_경계값2() {
        String str = "dahye";
        String result = str.substring(0, 0);
        assertEquals("", result);
    }

    @Test
    @DisplayName("substring의 endIndex가 문자열 길이보다 크면 예외가 발생한다.")
    void substring_예외() {
        String str = "dahye";
        assertThrows(StringIndexOutOfBoundsException.class, () -> {
            str.substring(0, 6);
        });
    }

    @Test
    @DisplayName("split은 특정 구분자로 문자열을 나누어 배열로 반환한다.")
    void splitTest() {
        // given
        String str = "abc,def,ghi";

        // when
        String[] result = str.split(",");

        // then
        assertArrayEquals(new String[]{"abc", "def", "ghi"}, result);
    }

    @Test
    @DisplayName("split은 구분자가 없으면 원본 문자열을 요소로 하는 배열을 반환한다. (예외 X)")
    void split_구분자없는문자열() {
        String str = "abcdefghij";
        String[] result = str.split(",");
        assertArrayEquals(new String[]{"abcdefghij"}, result);
    }

    @Test
    @DisplayName("맨앞에 구분자가 있으면 빈 문자열이 요소로 포함된다. 맨뒤에 구분자가 있으면 빈 문자열이 포함되지 않는다.")
    void split_빈문자열() {
        String str1 = ",a,b";
        String str2 = "a,b,";

        String[] result1 = str1.split(",");
        String[] result2 = str2.split(",");

        assertArrayEquals(new String[]{"", "a", "b"}, result1);
        assertArrayEquals(new String[]{"a", "b"}, result2);
    }

    @Test
    @DisplayName("indexOf는 찾는 문자열의 시작 인덱스를 반환한다.")
    void indexOfTest() {
        // given
        String str = "abcdefghij";

        // when
        int index = str.indexOf("cde");

        // then
        assertEquals(2, index);
    }

    @Test
    @DisplayName("indexOf는 찾는 문자열이 없으면 -1을 반환한다. (예외 X)")
    void indexOf_없는문자열() {
        String str = "abcdefghij";
        int index = str.indexOf("xyz", 0);
        assertEquals(-1, index);
    }

    @Test
    @DisplayName("charAt은 해당 인덱스의 문자를 반환한다.")
    void charAtTest() {
        // given
        String str = "abc";

        // when
        char ch = str.charAt(0);

        // then
        assertEquals('a', ch);
    }

    @Test
    @DisplayName("음수 인덱스나 문자열 길이보다 크면 예외가 발생한다.")
    void charAt_예외() {
        String str = "abc";
        assertThrows(StringIndexOutOfBoundsException.class, () -> {
            str.charAt(-1);
        });
    }

    @Test
    @DisplayName("equals는 문자열이 같으면 true를 반환한다.")
    void equalsTest() {
        // given
        String str1 = "test";
        String str2 = "test";

        // when
        boolean result = str1.equals(str2);

        // then
        assertTrue(result);
    }

    @Test
    @DisplayName("equals는 문자열이 다르면 false를 반환한다. (대소문자 구분!)")
    void equals_다른문자열() {
        String str1 = "test";
        String str2 = "TEST";
        assertFalse(str1.equals(str2));
    }

    @Test
    @DisplayName("equals는 null과 비교하면 false를 반환한다.")
    void equals_null() {
        String str1 = "test";
        String str2 = null;
        assertFalse(str1.equals(str2));
    }

    @Test
    @DisplayName("equalsIgnoreCase는 대소문자를 무시하고 비교한다.")
    void equalsIgnoreCase_대소문자무시() {
        String str1 = "test";
        String str2 = "TEST";
        assertTrue(str1.equalsIgnoreCase(str2));
    }

    @Test
    @DisplayName("replace는 특정 문자열을 다른 문자열로 치환한다.")
    void replaceTest() {
        // given
        String str = "hello";

        // when
        String result = str.replace("hello", "world");

        // then
        assertEquals("world", result);
    }

    @Test
    @DisplayName("replace는 찾는 문자열이 없으면 원본 문자열을 반환한다. (예외 X)")
    void replace_없는문자열() {
        String str = "hello";
        String result = str.replace("xyz", "world");
        assertEquals("hello", result);
    }

    @Test
    @DisplayName("toLowerCase는 모든 문자를 소문자로 변환한다.")
    void lowerCaseTest() {
        // given
        String str = "HELLo";

        // when
        String result = str.toLowerCase();

        // then
        assertEquals("hello", result);
    }

    @Test
    @DisplayName("toUpperCase는 모든 문자를 대문자로 변환한다.")
    void upperCaseTest() {
        // given
        String str = "hello";

        // when
        String result = str.toUpperCase();

        // then
        assertEquals("HELLO", result);
    }

    @Test
    @DisplayName("trim은 문자열 양쪽 공백을 제거한다. (중간 공백은 제거 X)")
    void trimTest() {
        // given
        String str = "  Thursday    ";
        String str2 = "Thurs   day";

        // when
        String result = str.trim();
        String result2 = str2.trim();

        // then
        assertEquals("Thursday", result);
        assertEquals("Thurs   day", result2);
    }

    @Test
    @DisplayName("trim은 공백이 없으면 원본 문자열을 반환한다. (예외 X)")
    void trim_공백없는문자열() {
        String str = "Thursday";
        String result = str.trim();
        assertEquals("Thursday", result);
    }

    @Test
    @DisplayName("trim은 공백만 있으면 빈 문자열을 반환한다.")
    void trim_빈문자열() {
        String str = "     ";
        String result = str.trim();
        assertEquals("", result);
    }

    @Test
    @DisplayName("valueOf는 기본 타입을 문자열로 변환한다.")
    void valueOfIntTest() {
        // given
        int number = 1000;
        double doubleNumber = 2.5;
        boolean bool = true;

        // when
        String result1 = String.valueOf(number);
        String result2 = String.valueOf(bool);
        String result3 = String.valueOf(doubleNumber);

        // then
        assertEquals("1000", result1);
        assertEquals("true", result2);
        assertEquals("2.5", result3);
    }
}
