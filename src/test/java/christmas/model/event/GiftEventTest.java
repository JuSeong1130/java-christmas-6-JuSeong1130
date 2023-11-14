package christmas.model.event;

import christmas.model.menu.DrinkMenu;
import christmas.model.menu.MainMenu;
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

class GiftEventTest {

    @Test
    @DisplayName("120000 이상이면 샴페인을 증정 받는다")
    void 증정_이벤트() {
        // given
        OrderItem orderItem = new OrderItem(
                new Menu(MenuType.MAIN_COURSE, MainMenu.BARBECUE_RIBS.getMenuName(),
                        MainMenu.BARBECUE_RIBS.getPrice()), new OrderQuantity(5));
        OrderItem orderItem1 = new OrderItem(
                new Menu(MenuType.MAIN_COURSE, MainMenu.CHRISTMAS_PASTA.getMenuName(),
                        MainMenu.CHRISTMAS_PASTA.getPrice()), new OrderQuantity(1));
        OrderItem orderItem2 = new OrderItem(
                new Menu(MenuType.DRINK, DrinkMenu.RED_WINE.getMenuName(),
                        DrinkMenu.RED_WINE.getPrice()), new OrderQuantity(3));
        Order order = new Order(new OrderItems(List.of(orderItem, orderItem1, orderItem2)),
                LocalDate.of(2023, 12, 22));
        Event event = new GiftEvent();

        String eventName = "증정 이벤트";
        int discountAmount = Gift.CHAMPAGNE.getPrice();
        DiscountResult actual = new DiscountResult(eventName, discountAmount);

        // when
        DiscountResult expected = event.calculateDiscountAmount(order);

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}