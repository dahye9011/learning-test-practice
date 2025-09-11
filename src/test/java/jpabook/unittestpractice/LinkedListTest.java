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
    @DisplayName("맨앞 혹은 맨뒤에 데이터를 추가한다.")
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
    @DisplayName("맨앞 혹은 맨뒤에 데이터를 삭제한다.")
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
    void p

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
}
