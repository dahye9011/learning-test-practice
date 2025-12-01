package unittestpractice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class DequeTest {
    @Test
    @DisplayName("덱의 기본 동작")
    public void deque_basic_behaviors() {
        Deque<Integer> deque = new ArrayDeque<>();

        // 뒤쪽 (큐처럼)
        deque.offerLast(1);
        deque.offerLast(2);
        deque.offerLast(3);

        // 1 2 3
        assertEquals(1, deque.peekFirst());
        assertEquals(3, deque.peekLast());

        // 앞쪽 (스택처럼)
        deque.offerFirst(4);
        assertEquals(4, deque.peekFirst());

        // 5 4 1 2 3
        deque.offerFirst(5);
        assertEquals(5, deque.peekFirst());
    }

    @Test
    @DisplayName("빈 덱일 때, peek/poll 계열은 null을 반환하고, get/remove 계열은 NoSuchElementException이 발생한다.")
    public void deque_empty_behaviors() {
        Deque<Integer> deque = new ArrayDeque<>();

        assertNull(deque.peekFirst());
        assertNull(deque.peekLast());
        assertNull(deque.pollFirst());
        assertNull(deque.pollLast());

        assertThrows(NoSuchElementException.class, deque::getFirst);
        assertThrows(NoSuchElementException.class, deque::getLast);
        assertThrows(NoSuchElementException.class, deque::removeFirst);
        assertThrows(NoSuchElementException.class, deque::removeLast);
    }

    @Test
    @DisplayName("ArrayDeque으로 Stack 동작 구현이 가능하다. 빈 덱일 때, pop은 NoSuchElementException이 발생한다.")
    public void deque_stack_test() {
        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.pop());
        assertEquals(2, stack.peek());

        stack.clear();

        assertNull(stack.peek()); // 스택과 달리 null 반환
        assertThrows(NoSuchElementException.class, stack::pop);
    }
}
