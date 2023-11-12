package christmas.model.menu;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

class MenusTest {

    @Test
    @DisplayName("보유하지 않은 메뉴이름을 입력하면 예외")
    void 메뉴검색_예외_발생() {
        // given
        String exceptionName = "테스트";
        Menus menus = new Menus();

        // when then
        Assertions.assertThatThrownBy(() -> menus.findMenuBy(exceptionName))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보유한 이름을 입력하면 예외없이 반환")
    void 메뉴검색_예외없이_성공() {
        // given
        String name = "양송이수프";
        Menus menus = new Menus();
        // when then
        Assertions.assertThatCode(() -> menus.findMenuBy(name))
                .doesNotThrowAnyException();
    }
}