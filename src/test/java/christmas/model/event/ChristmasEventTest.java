package christmas.model.event;

import static org.junit.jupiter.api.Assertions.*;

import christmas.model.menu.DessertMenu;
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

class ChristmasEventTest {
    @Test
    @DisplayName("12월 1일에서 25일 사이라면 기본할인 1000원에 1일부터 지난 일수 * 100원이 할인된다.")
    void 크리스마스_할인_이벤트() {
        // given
        OrderItem dessertItem = new OrderItem(
                new Menu(MenuType.DESSERT, DessertMenu.CHOCOLATE_CAKE.getMenuName(),
                        DessertMenu.CHOCOLATE_CAKE.getPrice()), 5);
        Order order = new Order(List.of(dessertItem),
                LocalDate.of(2023, 12, 12));
        Event event = new ChristmasEvent();

        String eventName = "크리스마스 디데이 할인";
        long discountAmount = (100 * 11) + 1000;
        DiscountResult actual = new DiscountResult(eventName, discountAmount);

        // when
        DiscountResult expected = event.calculateDiscountAmount(order);

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}