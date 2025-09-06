package jpabook.unittestpractice;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class ArraysTest {
    @Test
    @DisplayName("Arrays.sort는 배열을 오름차순으로 정렬한다. (assertArrayEquals로 비교)")
    void sortTest() {
        // given
        int[] arr = {4, 3, 2, 1};

        // when
        Arrays.sort(arr);

        // then
        assertArrayEquals(new int[]{1, 2, 3, 4}, arr);
    }

    @Test
    @DisplayName("내림차순으로 정렬하려면 객체 배열을 사용하고, Collections.reverseOrder()를 사용한다.")
    void reverseTest() {
        // given
        Integer[] arr = {1, 2, 3, 4};

        // when
        Arrays.sort(arr, Collections.reverseOrder());

        // then
        assertArrayEquals(new Integer[]{4, 3, 2, 1}, arr);
    }

    @Test
    @DisplayName("Comparator를 사용하여 내림차순 정렬을 구현할 수 있다.")
    void reverseTest2() {
        // given
        Integer[] arr = {1, 2, 3, 4};

        // when
        Arrays.sort(arr, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });

        // then
        assertArrayEquals(new Integer[]{4, 3, 2, 1}, arr);
    }

    @Test
    @DisplayName("람다식을 사용하여 내림차순 정렬을 구현할 수 있다.")
    void reverseTest3() {
        // given
        Integer[] arr = {1, 2, 3, 4};

        // when
        Arrays.sort(arr, (a, b) -> Integer.compare(b, a));

        // then
        assertArrayEquals(new Integer[]{4, 3, 2, 1}, arr);
    }

    @Test
    @DisplayName("binarySearch는 이진 탐색을 수행한다.")
    void binarySearch() {
        // given
        int[] arr = {1, 3, 5, 7, 9};

        // when
        int index = Arrays.binarySearch(arr, 5);

        // then
        assertEquals(2, index);
    }

    @Test
    @DisplayName("키가 없으면 음수를 반환 (삽입될 위치의 음수 -1)")
    void binarySearchTest_키없음() {
        // given
        int[] arr = {1, 3, 5, 7, 9};

        // when
        int index = Arrays.binarySearch(arr, 6);

        // then
        assertEquals(-4, index);
    }

    @Test
    @DisplayName("빈 배열에서 탐색하면 -1을 반환 (0 - 1)")
    void binarySearchTest_빈배열() {
        int[] arr = {};
        int index = Arrays.binarySearch(arr, 1);
        assertEquals(-1, index);
    }

    @Test
    @DisplayName("정렬되지 않은 배열에서는 예측 불가능한 결과가 나온다.")
    void binarySearchTest_정렬안된배열() {
        int[] arr = {3, 1, 4, 2, 5};
        int index = Arrays.binarySearch(arr, 4);
        // 정렬되지 않은 배열에서의 결과는 예측 불가능하므로 특정 값을 기대하지 않음
        System.out.println(index);
    }

    @Test
    @DisplayName("copyOf는 배열의 일부를 복사하여 새로운 배열을 만든다.")
    void copyOfTest() {
        // given
        int[] arr = {1, 2, 3, 4, 5};

        // when
        int[] copiedArr = Arrays.copyOf(arr, 3);

        // then
        assertArrayEquals(new int[]{1, 2, 3}, copiedArr);
    }

    @Test
    @DisplayName("copyOf는 원본 배열보다 길게 복사하면 나머지 요소는 기본값으로 채운다.")
    void copyOfTest_원본보다길게복사() {
        int[] arr = {1, 2, 3};
        int[] copiedArr = Arrays.copyOf(arr, 5);
        assertArrayEquals(new int[]{1, 2, 3, 0, 0}, copiedArr);
    }

    @Test
    @DisplayName("copyOf는 음수 길이를 지정하면 NegativeArraySizeException이 발생한다.")
    void copyOfTest_음수길이() {
        int[] arr = {1, 2, 3};
        assertThrows(NegativeArraySizeException.class, () -> {
            int[] copiedArr = Arrays.copyOf(arr, -1);
        });
    }

    @Test
    @DisplayName("asList는 배열을 고정 크기 리스트로 변환한다. (add, remove 불가)")
    void asListTest() {
        // given
        String[] arr = {"a", "b", "c"};

        // when
        List<String> arrList = Arrays.asList(arr);

        // then
        assertEquals(3, arrList.size());
        assertEquals("a", arrList.get(0));
    }

    @Test
    @DisplayName("1차원 배열은 Arrays.equals로 내용을 비교한다 (assertArrayEquals 권장)")
    void equalsTest_1차원배열() {
        // given
        String[] arr = {"a", "b", "c"};
        String[] arr2 = {"a", "b", "c"};

        // when
        boolean result = Arrays.equals(arr, arr2);

        // then
        assertTrue(result);
        assertArrayEquals(arr, arr2);
    }
    
    @Test
    @DisplayName("2차원 배열의 내용 비교는 deepEquals를 사용한다.")
    void equalsTest_2차원배열() {
        // given
        String[][] arr = {{"a", "b"},
                {"c", "d"}
        };
        String[][] arr2 = {{"a", "b"},
                {"c", "d"}
        };

        // when
        boolean result = Arrays.deepEquals(arr, arr2);

        // then
        assertTrue(result);
    }
}
