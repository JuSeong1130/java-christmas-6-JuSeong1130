package christmas.model.order;

import static org.junit.jupiter.api.Assertions.*;

import christmas.model.menu.Menu;
import christmas.model.menu.MenuCategory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

class OrderItemTest {

    @Test
    @DisplayName("주문한 수량이 1개 미만이라면 예외 발생")
    void OrderItem_생성_예외_테스트() {
        // given
        int quantity = 0;
        Menu menu = new Menu("테스트", 0);

        // when then
        Assertions.assertThatThrownBy(() -> new OrderItem(menu, quantity))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("주문한 수량이 1개 이상이라면 예외 없이 생성 성공")
    void OrderItem_생성_성공_테스트() {
        // given
        int quantity = 1;
        Menu menu = new Menu("테스트", 0);

        // when then
        Assertions.assertThatCode(() -> new OrderItem(menu, quantity))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("입력된 메뉴가 드링크 메뉴라면 True")
    void 드링크_메뉴_True() {
        // given
        String drinkName = "제로콜라";
        int price = 3000;
        Menu menu = new Menu(drinkName, price);
        OrderItem orderItem = new OrderItem(menu, price);
        // when
        boolean isDrinkMenu = orderItem.isDrinkMenu();

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
        OrderItem orderItem = new OrderItem(menu, price);

        // when
        boolean isDrinkMenu = orderItem.isDrinkMenu();

        //then
        Assertions.assertThat(isDrinkMenu).isFalse();
    }
}