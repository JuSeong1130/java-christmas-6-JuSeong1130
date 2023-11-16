package christmas.model.event;

import static org.junit.jupiter.api.Assertions.*;

import christmas.config.AppConfig;
import christmas.model.menu.DessertMenu;
import christmas.model.menu.DrinkMenu;
import christmas.model.menu.MainMenu;
import christmas.model.menu.Menu;
import christmas.model.menu.MenuType;
import christmas.model.order.Order;
import christmas.model.order.OrderItem;
import christmas.model.order.OrderItems;
import christmas.model.order.OrderQuantity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChristmasEventTest {

    private static int BASIC_AMOUNT = 1000;
    private static int OPERATION_VALUE = 100;

    @Test
    @DisplayName("날짜가 12월 1일에서 25일 사이라면 기본할인 1000원에 1일부터 지난 일수 * 100원이 할인된다.")
    void 크리스마스_할인_이벤트() {
        // given
        OrderItem dessertItem = new OrderItem(
                new Menu(MenuType.DESSERT, DessertMenu.CHOCOLATE_CAKE.getMenuName(),
                        DessertMenu.CHOCOLATE_CAKE.getPrice()), new OrderQuantity(5));

        int day = 12;

        Order order = new Order(new OrderItems(List.of(dessertItem)),
                LocalDate.of(2023, 12, day));
        Event event = AppConfig.createChristmasEvent();

        String eventName = "크리스마스 디데이 할인";
        int discountAmount = (OPERATION_VALUE * (day - 1)) + BASIC_AMOUNT;
        DiscountResult actual = new DiscountResult(eventName, discountAmount);

        // when
        DiscountResult expected = event.calculateDiscountAmount(order);

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}