package unittestpractice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SetTest {
    @Test
    @DisplayName("Set의 기본 동작")
    public void hashSet_basic_behaviors() {
        // Set 인터페이스로 선언하고, HashSet 구현체 사용
        Set<Integer> hs = new HashSet<>();

        assertTrue(hs.add(10));
        assertTrue(hs.add(20));
        assertTrue(hs.add(30));

        // 중복 불가 => 중복 add는 false 반환, 크기도 그대로
        assertFalse(hs.add(20));
        assertEquals(3, hs.size());

        // 원소 포함 검증 (순서 무관!)
        assertTrue(hs.containsAll(Set.of(10, 20, 30)));

        // 순회 검증 (한 번 순회하면 소진됨)
        Iterator<Integer> it = hs.iterator();
        Set<Integer> newHashSet = new HashSet<>();
        while (it.hasNext()) newHashSet.add(it.next());

        // 순회로 모은 집합 == 원본 집합 (순서 검증 X, 집합 동등성만)
        assertEquals(hs, newHashSet);

        // 소진됐으므로 더 이상 원소 없음
        assertFalse(it.hasNext());

        // 새 Iterator로 다시 처음부터 순회 가능
        Iterator<Integer> it2 = hs.iterator();
        assertTrue(it2.hasNext());
    }
}
