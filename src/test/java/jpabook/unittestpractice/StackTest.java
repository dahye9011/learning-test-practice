package jpabook.unittestpractice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {
    @Test
    @DisplayName("스택 기본 동작")
    public void stack_basic_behaviors() {
        Stack<Integer> stack = new Stack<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.size());

        // 맨 위의 요소 반환
        assertEquals(3, stack.peek());

        // 맨 위의 요소를 삭제하고 반환
        assertEquals(3, stack.pop());
        assertEquals(2, stack.peek());
        assertEquals(2, stack.size());

        // 모든 요소 제거
        stack.clear();
        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("빈 스택일 때 peek/pop을 하면 EmptyStackException이 발생한다.")
    public void empty_stack_exception() {
        Stack<Integer> stack = new Stack<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        stack.clear();

        assertThrows(EmptyStackException.class, stack::peek);
        assertThrows(EmptyStackException.class, stack::pop);
    }
}
