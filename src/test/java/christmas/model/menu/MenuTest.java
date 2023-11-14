package christmas.model.menu;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {

    @Test
    @DisplayName("입력된 이름과 Menu가 가지고 있는 이름이 같다면 True")
    void 입력된_이름_같다면_True() {

        DrinkMenu drinkMenu = DrinkMenu.CHAMPAGNE;
        // given
        Menu menu = new Menu(drinkMenu.getMenuType(), drinkMenu.getMenuName(),
                drinkMenu.getPrice());
        String menuName = drinkMenu.getMenuName();
        // when then
        Assertions.assertThat(menu.isEqualName(menuName)).isTrue();
    }

    @Test
    @DisplayName("입력된 이름과 Menu가 가지고 있는 이름이 다르다면 False")
    void 입력된_이름_다르다면_False() {
        DrinkMenu drinkMenu = DrinkMenu.CHAMPAGNE;
        // given
        Menu menu = new Menu(drinkMenu.getMenuType(), drinkMenu.getMenuName(),
                drinkMenu.getPrice());
        String menuName = "샴페인아님";
        // when then
        Assertions.assertThat(menu.isEqualName(menuName)).isFalse();
    }
}