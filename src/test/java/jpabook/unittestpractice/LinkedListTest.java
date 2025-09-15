package jpabook.unittestpractice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class LinkedListTest {
    @Test
    @DisplayName("add로 데이터를 추가한다.")
    void addTest() {
        // given
        LinkedList<String> list = new LinkedList<>();

        // when
        list.add("A"); // 맨 뒤에 추가
        list.add("B");
        list.add("C");
        list.add(2, "X");

        // then
        assertEquals(4, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("X", list.get(2));
    }

    @Test
    @DisplayName("addFirst로 맨앞에 데이터를 추가하고, addLast로 맨뒤에 데이터를 추가한다.")
    void addFirstAndLastTest() {
        // given
        LinkedList<String> list = new LinkedList<>(Arrays.asList("A", "B", "C"));

        // when
        list.addFirst("X");
        list.addLast("Y");

        // then
        assertEquals(5, list.size());
        assertEquals("X", list.get(0));
        assertEquals("Y", list.get(4));
    }

    @Test
    @DisplayName("remove로 데이터를 삭제한다.")
    void removeTest() {
        // given
        LinkedList<String> list = new LinkedList<>(Arrays.asList("A", "B", "C"));

        // when
        list.remove(); // 맨 앞의 요소 삭제

        // then
        assertEquals(2, list.size());
        assertEquals("B", list.get(0));
    }

    @Test
    @DisplayName("removeFirst로 맨앞에 데이터를 삭제하고, removeLast로 맨뒤에 데이터를 삭제한다.")
    void removeFirstAndLastTest() {
        // given
        LinkedList<String> list = new LinkedList<>(Arrays.asList("A", "B", "C"));

        // when
        list.removeFirst();
        list.removeLast();

        // then
        assertEquals(1, list.size());
        assertEquals("B", list.get(0));
    }

    @Test
    @DisplayName("빈 LinkedList에 remove를 하면 NoSuchElementException이 발생한다.")
    void removeTest_예외() {
        LinkedList<String> list = new LinkedList<>();
        assertThrows(NoSuchElementException.class, list::remove);
    }

    @Test
    @DisplayName("인덱스 범위를 벗어나면 IndexOutOfBoundsException이 발생한다.")
    void indexOutOfBoundTest() {
        LinkedList<Number> list = new LinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(5, 4));
    }

    @Test
    @DisplayName("중간에 데이터를 삽입/삭제할 수 있다. 데이터의 이동은 없고, 앞뒤 노드의 참조만 변경된다.")
    void addAndRemoveInMiddleTest() {
        LinkedList<String> list = new LinkedList<>(Arrays.asList("A", "B", "C", "D"));

        list.add(1, "X"); // 중간 삽입
        assertEquals(Arrays.asList("A", "X", "B", "C", "D"), list);

        list.remove(2); // 중간 삭제
        assertEquals(Arrays.asList("A", "X", "C", "D"), list);
    }

    @Test
    @DisplayName("LinkedList는 null이나 중복 요소를 허용한다.")
    void nullAndDuplicatesTest() {
        LinkedList<String> list = new LinkedList<>();

        list.add(null);
        list.add("A");
        list.add("A");

        assertTrue(list.contains(null));
        assertEquals(3, list.size());
        assertEquals("A", list.get(1));
        assertEquals("A", list.getLast());
    }

    @Test
    @DisplayName("set은 해당 인덱스의 데이터를 변경한다.")
    void setTest() {
        // given
        LinkedList<String> list = new LinkedList<>();

        list.add("A");
        list.add("B");
        list.add("C");

        // when
        list.set(0, "X");

        // then
        assertEquals(3, list.size());
        assertEquals("X", list.get(0));
    }

    @Test
    @DisplayName("toArray는 노드에 저장된 요소를 배열로 변환한다.")
    void toArrayTest() {
        // given
        LinkedList<String> list = new LinkedList<>();

        list.add("A");
        list.add("B");
        list.add("C");

        // when
        String[] array = list.toArray(new String[0]); // 리턴 타입이 Object[]이므로 타입 힌트

        // then
        assertArrayEquals(new String[]{"A", "B", "C"}, array);
    }

    @Test
    void iteratorTest() {
        // given
        LinkedList<String> list = new LinkedList<>();

        list.add("A");
        list.add("B");
        list.add("C");

        // when
        Iterator<String> iterator = list.iterator();

        // then
        assertTrue(iterator.hasNext());
        assertEquals("A", iterator.next());
    }

    @Test
    @DisplayName("contain은 요소가 LinkedList에 포함되어 있는지 확인한다.")
    void containTest() {
        // given
        LinkedList<String> list = new LinkedList<>();

        list.add("A");
        list.add("B");
        list.add("C");

        // when
        boolean result = list.contains("A");

        // then
        assertTrue(result);
    }


    @Test
    @DisplayName("containsAll은 모든 요소가 LinkedList에 포함되어 있는지 확인한다.")
    void containsAllTest() {
        // given
        LinkedList<String> list1 = new LinkedList<>();

        list1.add("A");
        list1.add("B");
        list1.add("C");

        LinkedList<String> list2 = new LinkedList<>();

        list2.add("A");
        list2.add("B");

        // when
        boolean result = list1.containsAll(list2);

        // then
        assertTrue(result);
    }

    @Test
    @DisplayName("clear는 LinkedList의 모든 요소를 삭제한다.")
    void clearTest() {
        // given
        LinkedList<Number> list = new LinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);

        // when
        list.clear();

        // then
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }
}
