package christmas.model.menu;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTypeTest {

    @Test
    @DisplayName("입력된 메뉴가 드링크 메뉴라면 True")
    void 드링크_메뉴_True() {
        // given
        MenuType menuType = MenuType.DRINK;
        // when
        boolean isDrinkMenu = menuType.isDrinkMenu();

        //then
        Assertions.assertThat(isDrinkMenu).isTrue();
    }

    @Test
    @DisplayName("입력된 메뉴가 드링크 메뉴가 아니라면 False")
    void 드링크_메뉴_False() {
        // given
        MenuType menuType = MenuType.MAIN_COURSE;

        // when
        boolean isDrinkMenu = menuType.isDrinkMenu();

        //then
        Assertions.assertThat(isDrinkMenu).isFalse();
    }


}