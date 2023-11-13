package christmas.model.event;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BadgeTest {

    @ParameterizedTest(name = "할인 금액이 {0}일때 뱃지 {1}")
    @DisplayName("할인 금액에 따른 Badge 찾는 기능 테스트")
    @MethodSource("argumentsDiscountAmountAndBadge")
    void 할인금액에_따른_뱃지_찾기_테스트(int discountAmount, Badge badge) {
        // given
        String actual = badge.getName();

        // when
        String expected = Badge.findBadgeBy(discountAmount);

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    public static Stream<Arguments> argumentsDiscountAmountAndBadge() {
        return Stream.of(
                Arguments.of(0, Badge.NONE),
                Arguments.of(5000, Badge.STAR),
                Arguments.of(10000, Badge.TREE),
                Arguments.of(20000, Badge.SANTA)
        );
    }
}