package christmas.model.menu;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

class MenuCategoryTest {

    @Test
    @DisplayName("입력된 메뉴가 드링크 메뉴라면 True")
    void 드링크_메뉴_True() {
        // given
        String drinkName = "제로콜라";
        int price = 3000;
        Menu menu = new Menu(drinkName, price);
        // when
        boolean isDrinkMenu = MenuCategory.isDrinkMenu(menu);

        //then
        Assertions.assertThat(isDrinkMenu).isTrue();
    }

    @Test
    @DisplayName("입력된 메뉴가 드링크 메뉴가 아니라면 False")
    void 드링크_메뉴_False() {
        // given
        String drinkName = "드링크아님";
        int price = 3000;
        Menu menu = new Menu(drinkName, price);
        // when
        boolean isDrinkMenu = MenuCategory.isDrinkMenu(menu);

        //then
        Assertions.assertThat(isDrinkMenu).isFalse();
    }


}