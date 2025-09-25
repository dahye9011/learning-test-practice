package jpabook.unittestpractice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {
    @Test
    @DisplayName("큐의 기본 동작")
    public void queue_basic_behaviors() {
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        assertEquals(3, queue.size());

        // 맨앞의 요소 반환
        assertEquals(1, queue.peek());

        // 맨앞의 요소를 삭제하고 반환
        assertEquals(1, queue.poll());
        assertEquals(2, queue.peek());
        assertEquals(2, queue.size());

        // 모든 요소 제거
        queue.clear();

        // 빈 큐일 때는 null 반환
        assertNull(queue.peek());
        assertNull(queue.poll());
    }
}
