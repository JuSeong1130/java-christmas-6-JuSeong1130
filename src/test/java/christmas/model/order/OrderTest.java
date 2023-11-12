package christmas.model.order;

import static org.junit.jupiter.api.Assertions.*;

import christmas.model.menu.Menu;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

class OrderTest {

    @Test
    @DisplayName("중복된 OrderItem이 있다면 Order 생성 시 예외 발생")
    void 중복된_OrderItem_있다면_예외() {
        // given
        Menu menu = new Menu("티본스테이크", 3000);
        Menu otherMenu = new Menu("티본스테이크", 3000);

        OrderItem orderItem = new OrderItem(menu, 1);
        OrderItem otherOrderItem = new OrderItem(otherMenu, 1);
        List<OrderItem> orderItems = List.of(orderItem, otherOrderItem);
        // when then
        Assertions.assertThatThrownBy(() -> new Order(orderItems, LocalDateTime.now()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("메뉴의 총 수량이 20개가 초과된다면 Order 생성 시 예외 발생")
    void 총_수량이_20개_초과_시_예외() {
        // given
        Menu menu = new Menu("샴페인", 3000);
        Menu otherMenu = new Menu("티본스테이크", 3000);

        OrderItem orderItem = new OrderItem(menu, 10);
        OrderItem otherOrderItem = new OrderItem(otherMenu, 11);
        List<OrderItem> orderItems = List.of(orderItem, otherOrderItem);
        // when then
        Assertions.assertThatThrownBy(() -> new Order(orderItems, LocalDateTime.now()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("OrderItem이 드링크 메뉴만이라면 Order 생성 시 예외 발생")
    void 음료_메뉴만_주문_시_예외() {
        // given
        Menu menu = new Menu("레드와인", 60000);
        Menu otherMenu = new Menu("제로콜라", 3000);

        OrderItem orderItem = new OrderItem(menu, 1);
        OrderItem otherOrderItem = new OrderItem(otherMenu, 1);
        List<OrderItem> orderItems = List.of(orderItem, otherOrderItem);
        // when then
        Assertions.assertThatThrownBy(() -> new Order(orderItems, LocalDateTime.now()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("총 수량 20이하이며 중복으로 상품을 선택하지 않고 음료만 주문하지 않는다면 Order 생성 성공 ")
    void Order_예외_없이_생성() {
        // given
        Menu menu = new Menu("티본스테이크", 60000);
        Menu otherMenu = new Menu("제로콜라", 3000);

        OrderItem orderItem = new OrderItem(menu, 1);
        OrderItem otherOrderItem = new OrderItem(otherMenu, 1);
        List<OrderItem> orderItems = List.of(orderItem, otherOrderItem);
        // when then
        Assertions.assertThatCode(() -> new Order(orderItems, LocalDateTime.now()))
                .doesNotThrowAnyException();
    }
    @Test
    @DisplayName("")
    void Order_총_주문금액_테스트() {
        String name = "제로콜라";
        int price = 3000;
        int quantity = 1;
        Menu menu = new Menu(name, price);

        String otherName= "티본스테이크";
        int otherPrice = 55000;
        int otherQuantity = 2;
        Menu otherMenu = new Menu(otherName, otherPrice);

        int actual = (price * quantity) + (otherPrice * otherQuantity);

        OrderItem orderItem = new OrderItem(menu, quantity);
        OrderItem otherOrderItem = new OrderItem(otherMenu, otherQuantity);
        List<OrderItem> orderItems = List.of(orderItem, otherOrderItem);
        Order order = new Order(orderItems, LocalDateTime.now());

        //when
        int expected = order.totalPurchaseAmount();

        //then
        Assertions.assertThat(actual).isEqualTo(expected);


    }
}