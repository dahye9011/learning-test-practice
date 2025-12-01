package unittestpractice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MapTest {
    @Test
    @DisplayName("Map의 기본 동작")
    public void hashMap_basic_behaviors() {
        // Map 인터페이스로 선언하고, HashMap 구현체 사용
        Map<String, String> hm = new HashMap<>();

        // put => key-value 쌍 저장
        hm.put("key1", "value1");
        hm.put("key2", "value2");
        hm.put("key3", "value3");

        // key로 value 조회
        assertEquals("value1", hm.get("key1"));

        // 없는 key 조회 => null 반환
        assertNull(hm.get("nullKey"));

        // keySet() => key 집합 반환
        Set<String> keys = hm.keySet();
        assertTrue(keys.containsAll(Set.of("key1", "key2", "key3")));

        // Iterator로 순회 가능
        Iterator<String> it = keys.iterator();
        Set<String> newKeys = new HashSet<>();
        while (it.hasNext()) newKeys.add(it.next());
        assertEquals(keys, newKeys);

        // values() => value 컬렉션 반환
        Collection<String> values = hm.values();
        assertTrue(values.containsAll(List.of("value1", "value2", "value3")));

        // entrySet() => key-value 쌍 집합 반환
        Set<Map.Entry<String, String>> entries = hm.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String key = entry.getKey();
            String value = entry.getValue();
            assertEquals(hm.get(key), value);
        }

        // 중복된 key를 put하면 기존 value가 덮어쓰기 됨!
        hm.put("key1", "newValue1");
        assertEquals("newValue1", hm.get("key1"));
        assertEquals(3, hm.size()); // 크기는 그대로 (key는 중복 불가)
    }
}
