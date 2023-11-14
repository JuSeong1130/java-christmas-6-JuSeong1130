package christmas.model.order;

import christmas.model.menu.DrinkMenu;
import christmas.model.menu.Menu;
import christmas.model.menu.MenuType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderItemTest {

    @Test
    @DisplayName("주문한 수량이 1개 미만이라면 예외 발생")
    void OrderItem_생성_예외_테스트() {
        // given
        int quantity = 0;
        Menu menu = new Menu(MenuType.DRINK, "테스트", 0);

        // when then
        Assertions.assertThatThrownBy(() -> new OrderItem(menu, new OrderQuantity(quantity)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("주문한 수량이 1개 이상이라면 예외 없이 생성 성공")
    void OrderItem_생성_성공_테스트() {
        // given
        int quantity = 1;
        Menu menu = new Menu(MenuType.DRINK, "테스트", 0);

        // when then
        Assertions.assertThatCode(() -> new OrderItem(menu, new OrderQuantity(quantity)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("입력된 메뉴가 드링크 메뉴라면 True")
    void 드링크_메뉴_True() {
        // given
        String drinkName = "제로콜라";
        int price = 3000;
        Menu menu = new Menu(MenuType.DRINK, drinkName, price);
        OrderItem orderItem = new OrderItem(menu, new OrderQuantity(5));
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
        Menu menu = new Menu(MenuType.MAIN_COURSE, drinkName, price);
        OrderItem orderItem = new OrderItem(menu, new OrderQuantity(5));

        // when
        boolean isDrinkMenu = orderItem.isDrinkMenu();

        //then
        Assertions.assertThat(isDrinkMenu).isFalse();
    }

    @Test
    @DisplayName("주문 항목의 메뉴타입과 일치한다면 True")
    void 주문항목_메뉴타입_일치_True() {
        // given
        String drinkName = DrinkMenu.CHAMPAGNE.getMenuName();
        int price = DrinkMenu.CHAMPAGNE.getPrice();
        MenuType menuType = MenuType.DRINK;
        Menu menu = new Menu(menuType, drinkName, price);
        OrderItem orderItem = new OrderItem(menu, new OrderQuantity(5));

        // when
        boolean isEqualMenuType = orderItem.isEqualMenuType(menuType);

        // then
        Assertions.assertThat(isEqualMenuType).isTrue();
    }

    @Test
    @DisplayName("주문 항목의 메뉴타입과 일치하지 않는다면 False")
    void 주문항목_메뉴타입_일치X_False() {
        // given
        String drinkName = DrinkMenu.CHAMPAGNE.getMenuName();
        int price = DrinkMenu.CHAMPAGNE.getPrice();
        MenuType menuType = MenuType.DRINK;
        Menu menu = new Menu(menuType, drinkName, price);
        OrderItem orderItem = new OrderItem(menu, new OrderQuantity(5));

        // when
        boolean isEqualMenuType = orderItem.isEqualMenuType(MenuType.MAIN_COURSE);

        // then
        Assertions.assertThat(isEqualMenuType).isFalse();
    }
}