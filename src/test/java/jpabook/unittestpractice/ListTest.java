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
        // list.remove("C"); remove(Object o)는 해당 값을 찾아 삭제 (첫 번째 발생만)
        list.remove(2);

        // then
        assertEquals(2, list.size());
        assertIterableEquals(List.of("A", "B"), list);
    }

    @Test
    @DisplayName("없는 인덱스에 remove를 하면 IndexOutOfBoundsException이 발생한다.")
    void removeTest_없는인덱스() {
        List<String> list = new ArrayList<>(List.of("A", "B", "C"));
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
        List<String> list = new ArrayList<>(List.of("A", "B", "C"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(5));
    }

    @Test
    @DisplayName("subList는 fromIndex부터 toIndex - 1까지의 요소를 반환한다.")
    void subListTest() {
        // given
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E"));

        // when
        List<String> result = list.subList(1, 4);

        // then
        assertIterableEquals(List.of("B", "C", "D"), result);
        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("subList는 원본 리스트와 연결된 뷰다.")
    void subListViewTest() {
        List<String> list = new ArrayList<>(List.of("A", "B", "C", "D", "E"));
        List<String> sub = list.subList(1, 4); // B, C, D

        sub.set(0, "X");
        assertEquals("X", list.get(1));

        list.set(3, "Y");
        assertEquals("Y", sub.get(2));
    }

    @Test
    @DisplayName("subList add/remove는 원본에 반영된다")
    void subListViewTest2() {
        List<String> list = new ArrayList<>(List.of("A", "B", "C", "D", "E"));
        List<String> sub = list.subList(1, 4); // B, C, D

        sub.add("X"); // 부모 리스트의 toIndex 위치에 삽입
        sub.remove("C");

        assertIterableEquals(List.of("A", "B", "D", "X", "E"), list);
        assertIterableEquals(List.of("B", "D", "X"), sub);
    }

    @Test
    @DisplayName("없는 인덱스에 subList를 하면 IndexOutOfBoundsException이 발생한다.")
    void subList_없는인덱스() {
        List<String> list = new ArrayList<>(List.of("A", "B", "C"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.subList(-1, 4));
    }

    @Test
    @DisplayName("fromIndex > toIndex인 경우 IllegalArgumentException이 발생한다.")
    void subList_인덱스순서() {
        List<String> list = new ArrayList<>(List.of("A", "B", "C"));
        assertThrows(IllegalArgumentException.class, () -> list.subList(3, 1));
    }

    @Test
    @DisplayName("원본 리스트의 구조를 변경하고, subList한 리스트의 메서드를 호출하면 ConcurrentModificationException이 발생한다.")
    void subList_구조변경() {
        List<String> list = new ArrayList<>(List.of("A", "B", "C", "D", "E"));
        List<String> sub = list.subList(1, 4); // B, C, D

        list.add("X");

        assertThrows(ConcurrentModificationException.class, sub::size);
        assertThrows(ConcurrentModificationException.class, () -> sub.get(0));
    }

    @Test
    @DisplayName("set은 해당 인덱스의 데이터를 변경한다.")
    void setTest() {
        // given
        List<String> list = new ArrayList<>();

        list.add("A");
        list.add("B");
        list.add("C");

        // when
        list.set(0, "D");

        // then
        assertEquals("D", list.get(0));
    }

    @Test
    @DisplayName("없는 인덱스에 set을 하면 IndexOutOfBoundsException가 발생한다.")
    void setTest_없는인덱스() {
        List<String> list = new ArrayList<>();

        list.add("A");
        list.add("B");
        list.add("C");

        assertThrows(IndexOutOfBoundsException.class, () -> list.set(5, "D"));
    }

    @Test
    @DisplayName("size는 리스트에 저장된 데이터의 개수를 반환한다.")
    void sizeTest() {
        // given
        List<String> list = new ArrayList<>(List.of("A", "B", "C"));

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
        List<String> list = new ArrayList<>(List.of("A", "B", "C"));

        // when
        boolean result = list.contains("A");

        // then
        assertTrue(result);
    }

    @Test
    @DisplayName("indexOf는 해당 데이터의 인덱스를 반환한다.")
    void indexOfTest() {
        // given
        List<String> list = new ArrayList<>(List.of("A", "B", "C"));

        // when
        int index = list.indexOf("B");

        // then
        assertEquals(1, index);
    }

    @Test
    @DisplayName("lastIndexOf는 중복 요소가 있을 때, 마지막 위치를 찾는다.")
    void lastIndexOfTest() {
        // given
        List<String> list = new ArrayList<>(List.of("A", "B", "C", "B", "B"));

        // when
        int lastIndex = list.lastIndexOf("B");

        // then
        assertEquals(4, lastIndex);
    }

    @Test
    @DisplayName("Arrays.asList는 크기 변경 연산(add/remove/clear)을 지원하지 않는다. (set은 가능)")
    void asListTest() {
        List<String> list = Arrays.asList("A", "B", "C");

        list.set(0, "D");

        assertAll(
                () -> assertThrows(UnsupportedOperationException.class, () -> list.add("D")),
                () -> assertThrows(UnsupportedOperationException.class, () -> list.remove("B")),
                () -> assertThrows(UnsupportedOperationException.class, list::clear)
        );
    }

    @Test
    @DisplayName("List.of는 크기 변경 연산(add/remove/set/clear)을 지원하지 않는다.")
    void ListOfTest() {
        List<String> list = List.of("A", "B", "C");

        assertAll(
                () -> assertThrows(UnsupportedOperationException.class, () -> list.add("D")),
                () -> assertThrows(UnsupportedOperationException.class, () -> list.remove("B")),
                () -> assertThrows(UnsupportedOperationException.class, () -> list.set(0, "D")),
                () -> assertThrows(UnsupportedOperationException.class, list::clear)
        );
    }

    @Test
    @DisplayName("List.of는 null 요소를 허용하지 않는다")
    void listOfTestNull() {
        assertThrows(NullPointerException.class, () -> List.of("A", null, "C"));
    }


    @Test
    @DisplayName("toArray는 리스트를 배열로 변환한다.")
    void toArrayTest() {
        // given
        List<Integer> list = List.of(1, 2, 3, 4, 5);

        // when
        Integer[] arr = list.toArray(new Integer[0]); // 크기 0은 무시, 타입만 맞추면 됨

        // then
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    @DisplayName("isEmpty는 리스트가 비어있는지 확인한다.")
    void isEmptyTest() {
        // given
        List<Integer> list = List.of(1, 2, 3, 4, 5);

        // when
        boolean result = list.isEmpty();

        // then
        assertFalse(result);
    }

    @Test
    @DisplayName("addAll은 여러 요소를 일괄 추가한다. (중복/순서 유지)")
    void addAllTest() {
        // given
        List<String> list = new ArrayList<>(List.of("A", "B", "C"));

        // when
        list.addAll(List.of("D", "E"));

        // then
        assertIterableEquals(List.of("A", "B", "C", "D", "E"), list);
    }

    @Test
    @DisplayName("removeAll은 인자로 준 컬렉션에 포함된 값과 같은 모든 원소를 삭제한다.")
    void removeAllTest() {
        // given
        List<String> list = new ArrayList<>(List.of("A", "B", "C", "B", "C", "C"));

        // when
        list.removeAll(List.of("A", "C"));

        // then
        assertIterableEquals(List.of("B", "B"), list);
    }

    @Test
    @DisplayName("retainAll은 인자로 준 컬렉션에 포함된 값만 남기고 나머지는 제거한다. (중복/순서 유지)")
    void retainAllTest() {
        // given
        List<String> list = new ArrayList<>(List.of("A", "B", "C", "D", "E", "A"));

        // when
        list.retainAll(List.of("A", "E"));

        // then
        assertIterableEquals(List.of("A", "E", "A"), list);
    }

    @Test
    @DisplayName("구현체가 달라도 요소/순서가 같으면 equals/hashCode가 일치한다.")
    void equalsAcrossImplementations() {
        List<String> a = new ArrayList<>(List.of("A","B","C"));
        List<String> b = new LinkedList<>(List.of("A","B","C"));

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }
}
