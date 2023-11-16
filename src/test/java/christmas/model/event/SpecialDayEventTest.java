package christmas.model.event;

import static org.junit.jupiter.api.Assertions.*;

import christmas.config.AppConfig;
import christmas.model.menu.DessertMenu;
import christmas.model.menu.Menu;
import christmas.model.menu.MenuType;
import christmas.model.order.Order;
import christmas.model.order.OrderItem;
import christmas.model.order.OrderItems;
import christmas.model.order.OrderQuantity;
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
                        DessertMenu.CHOCOLATE_CAKE.getPrice()), new OrderQuantity(5));
        int specialDay = 3;
        Order order = new Order(new OrderItems(List.of(dessertItem)),
                LocalDate.of(2023, 12, specialDay));
        Event event = AppConfig.createSpecialDayEvent();

        String eventName = "특별 할인";
        int discountAmount = 1000;
        DiscountResult actual = new DiscountResult(eventName, discountAmount);

        // when
        DiscountResult expected = event.calculateDiscountAmount(order);

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}