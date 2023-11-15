package christmas.model.event;

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
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventPlannerTest {

    @Test
    @DisplayName("보유 하고 있는 이벤트를 적용하여 할인결과들을 알려준다.")
    void 할인결과_리턴() {
        // given
        Order order = getOrder();
        List<Event> events = getEvents();
        EventPlanner eventPlanner = new EventPlanner(events);
        DiscountResults actual = getDiscountResults();

        // when
        DiscountResults expected = eventPlanner.calculateDiscountAmount(order);

        // then
        Assertions.assertThat(actual.getDiscountResults())
                .containsExactlyInAnyOrderElementsOf(expected.getDiscountResults());
    }

    private static Order getOrder() {
        OrderItem dessertItem = new OrderItem(
                new Menu(MenuType.DESSERT, DessertMenu.CHOCOLATE_CAKE.getMenuName(),
                        DessertMenu.CHOCOLATE_CAKE.getPrice()), new OrderQuantity(5));
        OrderItem mainMenuItem = new OrderItem(
                new Menu(MenuType.MAIN_COURSE, MainMenu.CHRISTMAS_PASTA.getMenuName(),
                        MainMenu.CHRISTMAS_PASTA.getPrice()), new OrderQuantity(1));
        OrderItem drinkMenuItem = new OrderItem(
                new Menu(MenuType.DRINK, DrinkMenu.RED_WINE.getMenuName(),
                        DrinkMenu.RED_WINE.getPrice()), new OrderQuantity(3));
        return new Order(new OrderItems(List.of(dessertItem, mainMenuItem, drinkMenuItem)),
                LocalDate.of(2023, 12, 3));
    }

    private static List<Event> getEvents() {
        return List.of(AppConfig.createChristmasEvent(),
                AppConfig.createSpecialDayEvent(),
                AppConfig.createWeekdayEvent(),
                AppConfig.createWeekendEvent(), AppConfig.createGiftEvent());
    }

    private DiscountResults getDiscountResults() {
        DiscountResult christmas = new DiscountResult("크리스마스 디데이 할인", 1200);
        DiscountResult gift = new DiscountResult("증정 이벤트", 25000);
        DiscountResult special = new DiscountResult("특별 할인", 1000);
        DiscountResult weekday = new DiscountResult("평일 할인", 10115);
        DiscountResult weekend = new DiscountResult("주말 할인", 0);
        return new DiscountResults(List.of(christmas, gift, special, weekday, weekend));
    }
}