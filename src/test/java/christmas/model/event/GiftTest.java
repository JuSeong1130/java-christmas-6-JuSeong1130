package christmas.model.event;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

class GiftTest {

    @Test
    @DisplayName("금액이 일정 금액 이상일때 증정되는 상품의 금액을 리턴한다.")
    void 일정_금액_넘어_증정_있음() {
        // given
        int purchaseAmount = 120000;

        int actual = Gift.CHAMPAGNE.getPrice();

        // when
        int expected = Gift.findGiftAmountBy(purchaseAmount);

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("금액이 일정 금액을 넘지 못할때는 증정되는 것이 없어 0원을 리턴한다.")
    void 일정_금액_넘지_못해_증정_없음() {
        int purchaseAmount = 10000;

        int actual = 0;

        // when
        int expected = Gift.findGiftAmountBy(purchaseAmount);

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}