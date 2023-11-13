package christmas.model.event;

import static org.junit.jupiter.api.Assertions.*;

import christmas.model.menu.DessertMenu;
import christmas.model.menu.Menu;
import christmas.model.menu.MenuType;
import christmas.model.order.Order;
import christmas.model.order.OrderItem;
import java.time.LocalDate;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpecialDayEventTest {

    @Test
    @DisplayName("특정 날짜라면 1000원이 할인된다.")
    void 특별_할인_이벤트() {
        // given
        OrderItem dessertItem = new OrderItem(
                new Menu(MenuType.DESSERT, DessertMenu.CHOCOLATE_CAKE.getMenuName(),
                        DessertMenu.CHOCOLATE_CAKE.getPrice()), 5);
        Order order = new Order(List.of(dessertItem),
                LocalDate.of(2023, 12, 3));
        Event event = new SpecialDayEvent();

        String eventName = "특별 할인";
        long discountAmount = 1000;
        DiscountResult actual = new DiscountResult(eventName, discountAmount);

        // when
        DiscountResult expected = event.calculateDiscountAmount(order);

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}