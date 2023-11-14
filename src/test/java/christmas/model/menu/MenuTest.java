package christmas.model.menu;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {

    @Test
    @DisplayName("입력된 이름과 Menu가 가지고 있는 이름이 같다면 True")
    void 입력된_이름_같다면_True() {
        // given
        Menu menu = new Menu(MenuType.DRINK, "테스트", 0);
        String menuName = "테스트";
        // when then
        Assertions.assertThat(menu.isEqualName(menuName)).isTrue();
    }

    @Test
    @DisplayName("입력된 이름과 Menu가 가지고 있는 이름이 다르다면 False")
    void 입력된_이름_다르다면_False() {
        // given
        Menu menu = new Menu(MenuType.DRINK, "테스트", 0);
        String menuName = "테스트1";
        // when then
        Assertions.assertThat(menu.isEqualName(menuName)).isFalse();
    }
}