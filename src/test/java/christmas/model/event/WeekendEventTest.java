package christmas.model.event;

import static org.junit.jupiter.api.Assertions.*;

import christmas.model.menu.DrinkMenu;
import christmas.model.menu.MainMenu;
import christmas.model.menu.Menu;
import christmas.model.menu.MenuType;
import christmas.model.order.Order;
import christmas.model.order.OrderItem;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

class WeekendEventTest {

    @Test
    @DisplayName("주말이며 구매금액이 10000원 이상이라면 메인 메뉴 개수만큼 할인받는다.")
    void 주말_할인_이벤트() {
        // given
        OrderItem orderItem = new OrderItem(
                new Menu(MenuType.MAIN_COURSE, MainMenu.BARBECUE_RIBS.getMenuName(),
                        MainMenu.BARBECUE_RIBS.getPrice()), 5);
        OrderItem orderItem1 = new OrderItem(
                new Menu(MenuType.MAIN_COURSE, MainMenu.CHRISTMAS_PASTA.getMenuName(),
                        MainMenu.CHRISTMAS_PASTA.getPrice()), 1);
        OrderItem orderItem2 = new OrderItem(
                new Menu(MenuType.DRINK, DrinkMenu.RED_WINE.getMenuName(),
                        DrinkMenu.RED_WINE.getPrice()), 3);
        Order order = new Order(List.of(orderItem, orderItem1, orderItem2),
                LocalDate.of(2023, 12, 22));
        Event event = new WeekendEvent();

        String eventName = "주말 할인";
        long discountAmount = 6 * 2023;
        DiscountResult actual = new DiscountResult(eventName, discountAmount);

        // when
        DiscountResult expected = event.calculateDiscountAmount(order);

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

}