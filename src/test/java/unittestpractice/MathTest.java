package unittestpractice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MathTest {
    @Test
    @DisplayName("max()는 주어진 값 중 큰 값을 반환하고, min()는 주어진 값 중 작은 값을 반환한다.")
    void 최대_최소_테스트() {
        // given
        int n1 = 10;
        int n2 = 20;
        double n3 = 30.5;
        double n4 = 40.9;

        // when
        int maxResult = Math.max(n1, n2);
        double minResult = Math.min(n3, n4);

        // then
        assertThat(maxResult).isEqualTo(20);
        assertThat(minResult).isEqualTo(30.5);
    }

    @Test
    @DisplayName("round()는 주어진 값을 float -> int, double -> long 타입으로 반올림하여 정수를 반환한다.")
    void 반올림_정수_반환_테스트() {
        // given
        float n1 = 3.5f;  // float -> round 결과 int
        double n2 = 4.7;  // double -> round 결과 long

        // when
        int result1 = Math.round(n1);
        long result2 = Math.round(n2);

        // then
        assertThat(result1).isEqualTo(4);
        assertThat(result2).isEqualTo(5);
    }

    @Test
    @DisplayName("rint()는 주어진 값을 반올림하여 double로 반환한다.")
    void 반올림_소수_반환_테스트() {
        // given
        float n1 = 3.5f;
        double n2 = 4.7;

        // when
        double result1 = Math.rint(n1);
        double result2 = Math.rint(n2);

        // then
        assertThat(result1).isEqualTo(4.0);
        assertThat(result2).isEqualTo(5.0);
    }

    @Test
    @DisplayName("rint()는 .5에서 가장 가까운 double형 짝수로 반올림한다.")
    void rint_경계값_테스트() {
        assertThat(Math.rint(2.5)).isEqualTo(2.0); //  짝수 2
        assertThat(Math.rint(3.5)).isEqualTo(4.0); //  짝수 4
        assertThat(Math.rint(-1.5)).isEqualTo(-2.0); // 짝수 -2
        assertThat(Math.rint(-2.5)).isEqualTo(-2.0); // 짝수 -2
    }

    @Test
    @DisplayName("ceil()은 값을 올림한 결과를 double로 반환한다.")
    void 올림_테스트() {
        // given
        float n1 = 3.5f;
        double n2 = 4.4;

        // when
        double result1 = Math.ceil(n1);
        double result2 = Math.ceil(n2);

        // then
        assertThat(result1).isEqualTo(4.0);
        assertThat(result2).isEqualTo(5.0);
    }

    @Test
    @DisplayName("floor()는 값을 버림한 결과를 double로 반환한다.")
    void 버림_테스트() {
        // given
        float n1 = 3.5f;
        double n2 = 4.4;

        // when
        double result1 = Math.floor(n1);
        double result2 = Math.floor(n2);

        // then
        assertThat(result1).isEqualTo(3.0);
        assertThat(result2).isEqualTo(4.0);
    }
}
