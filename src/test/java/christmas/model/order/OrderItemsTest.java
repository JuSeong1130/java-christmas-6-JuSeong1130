package christmas.model.order;

import static org.junit.jupiter.api.Assertions.*;

import christmas.model.menu.Menu;
import christmas.model.menu.MenuType;
import java.time.LocalDate;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderItemsTest {

    @Test
    @DisplayName("중복된 OrderItem이 있다면 Order 생성 시 예외 발생")
    void 중복된_OrderItem_있다면_예외() {
        // given
        Menu menu = new Menu(MenuType.MAIN_COURSE, "티본스테이크", 3000);
        Menu otherMenu = new Menu(MenuType.MAIN_COURSE, "티본스테이크", 3000);

        OrderItem orderItem = new OrderItem(menu, new OrderQuantity(1));
        OrderItem otherOrderItem = new OrderItem(otherMenu, new OrderQuantity(1));
        List<OrderItem> orderItems = List.of(orderItem, otherOrderItem);
        // when then
        Assertions.assertThatThrownBy(() -> new OrderItems(orderItems))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("메뉴의 총 수량이 20개가 초과된다면 Order 생성 시 예외 발생")
    void 총_수량이_20개_초과_시_예외() {
        // given
        Menu menu = new Menu(MenuType.DRINK, "샴페인", 3000);
        Menu otherMenu = new Menu(MenuType.MAIN_COURSE, "티본스테이크", 3000);

        OrderItem orderItem = new OrderItem(menu, new OrderQuantity(10));
        OrderItem otherOrderItem = new OrderItem(otherMenu, new OrderQuantity(11));
        List<OrderItem> orderItems = List.of(orderItem, otherOrderItem);
        // when then
        Assertions.assertThatThrownBy(() -> new OrderItems(orderItems))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("OrderItem이 드링크 메뉴만이라면 Order 생성 시 예외 발생")
    void 음료_메뉴만_주문_시_예외() {
        // given
        Menu menu = new Menu(MenuType.DRINK, "레드와인", 60000);
        Menu otherMenu = new Menu(MenuType.DRINK, "제로콜라", 3000);

        OrderItem orderItem = new OrderItem(menu, new OrderQuantity(1));
        OrderItem otherOrderItem = new OrderItem(otherMenu, new OrderQuantity(1));
        List<OrderItem> orderItems = List.of(orderItem, otherOrderItem);
        // when then
        Assertions.assertThatThrownBy(() -> new OrderItems(orderItems))
                .isInstanceOf(IllegalArgumentException.class);
    }
}