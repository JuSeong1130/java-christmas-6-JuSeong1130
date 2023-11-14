package christmas.model.order;

import christmas.model.menu.DrinkMenu;
import christmas.model.menu.Menu;
import christmas.model.menu.MenuType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderItemTest {

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