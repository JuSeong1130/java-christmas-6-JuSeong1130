package christmas.model.order;

import static org.junit.jupiter.api.Assertions.*;

import christmas.model.menu.Menu;
import christmas.model.menu.MenuType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

class OrderTest {

    @Test
    @DisplayName("중복된 OrderItem이 있다면 Order 생성 시 예외 발생")
    void 중복된_OrderItem_있다면_예외() {
        // given
        Menu menu = new Menu(MenuType.MAIN_COURSE, "티본스테이크", 3000);
        Menu otherMenu = new Menu(MenuType.MAIN_COURSE, "티본스테이크", 3000);

        OrderItem orderItem = new OrderItem(menu, 1);
        OrderItem otherOrderItem = new OrderItem(otherMenu, 1);
        List<OrderItem> orderItems = List.of(orderItem, otherOrderItem);
        // when then
        Assertions.assertThatThrownBy(() -> new Order(orderItems, LocalDate.now()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("메뉴의 총 수량이 20개가 초과된다면 Order 생성 시 예외 발생")
    void 총_수량이_20개_초과_시_예외() {
        // given
        Menu menu = new Menu(MenuType.DRINK, "샴페인", 3000);
        Menu otherMenu = new Menu(MenuType.MAIN_COURSE, "티본스테이크", 3000);

        OrderItem orderItem = new OrderItem(menu, 10);
        OrderItem otherOrderItem = new OrderItem(otherMenu, 11);
        List<OrderItem> orderItems = List.of(orderItem, otherOrderItem);
        // when then
        Assertions.assertThatThrownBy(() -> new Order(orderItems, LocalDate.now()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("OrderItem이 드링크 메뉴만이라면 Order 생성 시 예외 발생")
    void 음료_메뉴만_주문_시_예외() {
        // given
        Menu menu = new Menu(MenuType.DRINK, "레드와인", 60000);
        Menu otherMenu = new Menu(MenuType.DRINK, "제로콜라", 3000);

        OrderItem orderItem = new OrderItem(menu, 1);
        OrderItem otherOrderItem = new OrderItem(otherMenu, 1);
        List<OrderItem> orderItems = List.of(orderItem, otherOrderItem);
        // when then
        Assertions.assertThatThrownBy(() -> new Order(orderItems, LocalDate.now()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("총 수량 20이하이며 중복으로 상품을 선택하지 않고 음료만 주문하지 않는다면 Order 생성 성공 ")
    void Order_예외_없이_생성() {
        // given
        Menu menu = new Menu(MenuType.MAIN_COURSE, "티본스테이크", 60000);
        Menu otherMenu = new Menu(MenuType.DRINK, "제로콜라", 3000);

        OrderItem orderItem = new OrderItem(menu, 1);
        OrderItem otherOrderItem = new OrderItem(otherMenu, 1);
        List<OrderItem> orderItems = List.of(orderItem, otherOrderItem);
        // when then
        Assertions.assertThatCode(() -> new Order(orderItems, LocalDate.now()))
                .doesNotThrowAnyException();
    }
    @Test
    @DisplayName("Order 총 주문 금액을 확인")
    void Order_총_주문금액_테스트() {
        String name = "제로콜라";
        int price = 3000;
        int quantity = 1;
        Menu menu = new Menu(MenuType.DRINK, name, price);

        String otherName= "티본스테이크";
        int otherPrice = 55000;
        int otherQuantity = 2;
        Menu otherMenu = new Menu(MenuType.MAIN_COURSE, otherName, otherPrice);

        int actual = (price * quantity) + (otherPrice * otherQuantity);

        OrderItem orderItem = new OrderItem(menu, quantity);
        OrderItem otherOrderItem = new OrderItem(otherMenu, otherQuantity);
        List<OrderItem> orderItems = List.of(orderItem, otherOrderItem);
        Order order = new Order(orderItems, LocalDate.now());

        //when
        int expected = order.totalPurchaseAmount();

        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Order의 주문일자가 주말이라면 True")
    void 주문일자_주말_Ture() {
        String name = "제로콜라";
        int price = 3000;
        int quantity = 1;
        Menu menu = new Menu(MenuType.DRINK, name, price);

        String otherName= "티본스테이크";
        int otherPrice = 55000;
        int otherQuantity = 2;
        Menu otherMenu = new Menu(MenuType.MAIN_COURSE, otherName, otherPrice);

        OrderItem orderItem = new OrderItem(menu, quantity);
        OrderItem otherOrderItem = new OrderItem(otherMenu, otherQuantity);
        List<OrderItem> orderItems = List.of(orderItem, otherOrderItem);
        Order order = new Order(orderItems, LocalDate.of(2023, 12 , 22));

        //when
        boolean expected = order.isWeekend();

        //then
        Assertions.assertThat(expected).isTrue();
    }
    @Test
    @DisplayName("Order의 주문일자가 주말이라면 False")
    void 주문일자_주말이_아닐_시_False() {
        String name = "제로콜라";
        int price = 3000;
        int quantity = 1;
        Menu menu = new Menu(MenuType.DRINK, name, price);

        String otherName= "티본스테이크";
        int otherPrice = 55000;
        int otherQuantity = 2;
        Menu otherMenu = new Menu(MenuType.MAIN_COURSE, otherName, otherPrice);

        OrderItem orderItem = new OrderItem(menu, quantity);
        OrderItem otherOrderItem = new OrderItem(otherMenu, otherQuantity);
        List<OrderItem> orderItems = List.of(orderItem, otherOrderItem);
        Order order = new Order(orderItems, LocalDate.of(2023, 12 , 21));

        //when
        boolean expected = order.isWeekend();

        //then
        Assertions.assertThat(expected).isFalse();
    }
    @Test
    @DisplayName("Order의 총 주문 금액이 특정한 금액을 넘는다면 True")
    void 총_주문금액_일정금액_넘을_시_True() {
        String name = "제로콜라";
        int price = 3000;
        int quantity = 1;
        Menu menu = new Menu(MenuType.DRINK, name, price);

        String otherName= "티본스테이크";
        int otherPrice = 55000;
        int otherQuantity = 2;
        Menu otherMenu = new Menu(MenuType.MAIN_COURSE, otherName, otherPrice);

        OrderItem orderItem = new OrderItem(menu, quantity);
        OrderItem otherOrderItem = new OrderItem(otherMenu, otherQuantity);
        List<OrderItem> orderItems = List.of(orderItem, otherOrderItem);
        Order order = new Order(orderItems, LocalDate.of(2023, 12 , 21));

        //when
        boolean expected = order.isTotalAmountAboveThan(10000);

        //then
        Assertions.assertThat(expected).isTrue();
    }

    @Test
    @DisplayName("Order의 총 주문 금액이 특정한 금액을 넘지 못하면 False")
    void 총_주문금액_일정금액_넘지_못할_시_False() {
        String name = "제로콜라";
        int price = 3000;
        int quantity = 1;
        Menu menu = new Menu(MenuType.DRINK, name, price);

        String otherName= "티본스테이크";
        int otherPrice = 55000;
        int otherQuantity = 2;
        Menu otherMenu = new Menu(MenuType.MAIN_COURSE, otherName, otherPrice);

        OrderItem orderItem = new OrderItem(menu, quantity);
        OrderItem otherOrderItem = new OrderItem(otherMenu, otherQuantity);
        List<OrderItem> orderItems = List.of(orderItem, otherOrderItem);
        Order order = new Order(orderItems, LocalDate.of(2023, 12 , 21));

        //when
        boolean expected = order.isTotalAmountAboveThan(120000);

        //then
        Assertions.assertThat(expected).isFalse();
    }

    @Test
    @DisplayName("Order의 특정 메뉴 타입이 몇개인지 확인하는 기능 테스트")
    void 특정_메뉴타입_개수_구하기_테스트() {
        String name = "제로콜라";
        int price = 3000;
        int quantity = 1;
        Menu menu = new Menu(MenuType.DRINK, name, price);

        String otherName= "티본스테이크";
        int otherPrice = 55000;
        int otherQuantity = 2;
        Menu otherMenu = new Menu(MenuType.MAIN_COURSE, otherName, otherPrice);

        OrderItem orderItem = new OrderItem(menu, quantity);
        OrderItem otherOrderItem = new OrderItem(otherMenu, otherQuantity);
        List<OrderItem> orderItems = List.of(orderItem, otherOrderItem);
        Order order = new Order(orderItems, LocalDate.of(2023, 12 , 21));

        int actual = 1;
        //when
        long expected = order.countMenuType(MenuType.DRINK);

        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Order가 특정 날짜들 사이에 있다면 True")
    void 특정날짜_사이_Ture() {
        String name = "제로콜라";
        int price = 3000;
        int quantity = 1;
        Menu menu = new Menu(MenuType.DRINK, name, price);

        String otherName= "티본스테이크";
        int otherPrice = 55000;
        int otherQuantity = 2;
        Menu otherMenu = new Menu(MenuType.MAIN_COURSE, otherName, otherPrice);

        OrderItem orderItem = new OrderItem(menu, quantity);
        OrderItem otherOrderItem = new OrderItem(otherMenu, otherQuantity);
        List<OrderItem> orderItems = List.of(orderItem, otherOrderItem);
        Order order = new Order(orderItems, LocalDate.of(2023, 12 , 21));

        LocalDate startDate = LocalDate.of(2023, 12, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        //when
        boolean expected = order.isTimeInRange(startDate, endDate);

        //then
        Assertions.assertThat(expected).isTrue();
    }

    @Test
    @DisplayName("Order가 특정 날짜들 사이가 아니라면 False")
    void 특정날짜_사이_아닐_시_False() {
        String name = "제로콜라";
        int price = 3000;
        int quantity = 1;
        Menu menu = new Menu(MenuType.DRINK, name, price);

        String otherName= "티본스테이크";
        int otherPrice = 55000;
        int otherQuantity = 2;
        Menu otherMenu = new Menu(MenuType.MAIN_COURSE, otherName, otherPrice);

        OrderItem orderItem = new OrderItem(menu, quantity);
        OrderItem otherOrderItem = new OrderItem(otherMenu, otherQuantity);
        List<OrderItem> orderItems = List.of(orderItem, otherOrderItem);
        Order order = new Order(orderItems, LocalDate.of(2023, 12 , 21));

        LocalDate startDate = LocalDate.of(2023, 12, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 20);
        //when
        boolean expected = order.isTimeInRange(startDate, endDate);

        //then
        Assertions.assertThat(expected).isFalse();
    }



}