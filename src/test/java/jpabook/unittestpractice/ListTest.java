package jpabook.unittestpractice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class ListTest {
    @Test
    @DisplayName("add는 리스트에 데이터를 추가한다.")
    void addTest() {
        // given
        List<String> list = new ArrayList<>();

        // when
        list.add("A");
        list.add("B");
        list.add("C");

        // then
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
        assertEquals(3, list.size());
    }

    @Test
    @DisplayName("remove는 리스트에 데이터를 삭제한다.")
    void removeTest() {
        // given
        List<String> list = new ArrayList<>();

        list.add("A");
        list.add("B");
        list.add("C");

        // when
        // list.remove("C");
        list.remove(2);

        // then
        assertEquals(2, list.size());
        assertIterableEquals(Arrays.asList("A", "B"), list);
    }

    @Test
    @DisplayName("없는 인덱스에 remove를 하면 IndexOutOfBoundsException이 발생한다.")
    void removeTest_없는인덱스() {
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(5));
    }

    @Test
    @DisplayName("get은 인덱스에 해당하는 데이터를 반환한다.")
    void getTest() {
        // given
        List<String> list = new ArrayList<>();

        list.add("A");
        list.add("B");
        list.add("C");

        // when
        String element = list.get(1);

        // then
        assertEquals("B", element);
    }

    @Test
    @DisplayName("없는 인덱스에 get을 하면 IndexOutOfBoundsException이 발생한다.")
    void getTest_없는인덱스() {
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(5));
    }

    @Test
    @DisplayName("set은 해당 인덱스의 데이터를 변경한다.")
    void setTest() {
        // given
        List<String> list = Arrays.asList("A", "B", "C");

        // when
        list.set(0, "D");

        // then
        assertEquals("D", list.get(0));
    }

    @Test
    @DisplayName("없는 인덱스에 set을 하면 IndexOutOfBoundsException가 발생한다.")
    void setTest_없는인덱스() {
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(5, "D"));
    }

    @Test
    @DisplayName("size는 리스트에 저장된 데이터의 개수를 반환한다.")
    void sizeTest() {
        // given
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));

        // when
        int result = list.size();

        // then
        assertEquals(3, result);
    }

    @Test
    @DisplayName("빈 리스트일 때, size는 0을 반환한다.")
    void sizeTest_빈리스트() {
        List<String> list = new ArrayList<>();
        assertEquals(0, list.size());
    }

    @Test
    @DisplayName("contains는 해당 데이터가 리스트에 포함되어 있는지 확인한다.")
    void containsTest() {
        // given
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));

        // when
        boolean result = list.contains("A");

        // then
        assertTrue(result);
    }

    @Test
    @DisplayName("indexOf는 해당 데이터의 인덱스를 반환한다.")
    void indexOfTest() {
        // given
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));

        // when
        int index = list.indexOf("B");

        // then
        assertEquals(1, index);
    }

    @Test
    @DisplayName("Arrays.asList는 크기 변경 연산(add/remove/clear)을 지원하지 않는다.")
    void asListTest() {
        List<String> list = Arrays.asList("A", "B", "C");

        assertAll(
                () -> assertThrows(UnsupportedOperationException.class, () -> list.add("D")),
                () -> assertThrows(UnsupportedOperationException.class, () -> list.remove("B")),
                () -> assertThrows(UnsupportedOperationException.class, list::clear)
        );
    }
}
