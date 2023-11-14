package christmas.model.event;

import static org.junit.jupiter.api.Assertions.*;

import christmas.model.menu.DessertMenu;
import christmas.model.menu.DrinkMenu;
import christmas.model.menu.MainMenu;
import christmas.model.menu.Menu;
import christmas.model.menu.MenuType;
import christmas.model.order.Order;
import christmas.model.order.OrderItem;
import christmas.model.order.OrderItems;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekdayEventTest {

    @Test
    @DisplayName("평일이며 구매금액이 10000원 이상이라면 디저트 메뉴 개수만큼 할인받는다.")
    void 평일_할인_이벤트() {
        // given
        OrderItem dessertItem = new OrderItem(
                new Menu(MenuType.DESSERT, DessertMenu.CHOCOLATE_CAKE.getMenuName(),
                        DessertMenu.CHOCOLATE_CAKE.getPrice()), 5);
        OrderItem mainMenuItem = new OrderItem(
                new Menu(MenuType.MAIN_COURSE, MainMenu.CHRISTMAS_PASTA.getMenuName(),
                        MainMenu.CHRISTMAS_PASTA.getPrice()), 1);
        OrderItem drinkMenuItem = new OrderItem(
                new Menu(MenuType.DRINK, DrinkMenu.RED_WINE.getMenuName(),
                        DrinkMenu.RED_WINE.getPrice()), 3);
        Order order = new Order(new OrderItems(List.of(dessertItem, mainMenuItem, drinkMenuItem)),
                LocalDate.of(2023, 12, 21));
        Event event = new WeekdayEvent();

        String eventName = "평일 할인";
        long discountAmount = 5 * 2023;
        DiscountResult actual = new DiscountResult(eventName, discountAmount);

        // when
        DiscountResult expected = event.calculateDiscountAmount(order);

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}