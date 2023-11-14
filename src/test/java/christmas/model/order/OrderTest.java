package christmas.model.order;

import static org.junit.jupiter.api.Assertions.*;

import christmas.model.menu.DrinkMenu;
import christmas.model.menu.MainMenu;
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

    int mainMenuQuantity;
    int drinkMenuQuantity;
    private Menu mainMenu;

    private Menu drinkMenu;

    private OrderItem mainOrderItem;

    private OrderItem drinkOrderItem;

    private OrderItems orderItems;

    @BeforeEach
    void setUp() {
        mainMenuQuantity = 2;
        drinkMenuQuantity = 1;

        MainMenu tBoneSteak = MainMenu.T_BONE_STEAK;
        mainMenu = new Menu(tBoneSteak.getMenuType(), tBoneSteak.getMenuName(),
                tBoneSteak.getPrice());

        DrinkMenu champagne = DrinkMenu.CHAMPAGNE;
        drinkMenu = new Menu(champagne.getMenuType(), champagne.getMenuName(),
                champagne.getPrice());

        mainOrderItem = new OrderItem(mainMenu, new OrderQuantity(mainMenuQuantity));
        drinkOrderItem = new OrderItem(drinkMenu, new OrderQuantity(drinkMenuQuantity));

        orderItems = new OrderItems(List.of(mainOrderItem, drinkOrderItem));
    }

    @Test
    @DisplayName("총 수량 20이하이며 중복으로 상품을 선택하지 않고 음료만 주문하지 않는다면 Order 생성 성공 ")
    void Order_예외_없이_생성() {
        // when then
        Assertions.assertThatCode(() -> new Order(orderItems, LocalDate.now()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Order의 주문일자가 주말이라면 True")
    void 주문일자_주말_Ture() {
        //given
        int weekday = 22;
        Order order = new Order(orderItems, LocalDate.of(2023, 12, weekday));

        //when
        boolean expected = order.isWeekend();

        //then
        Assertions.assertThat(expected).isTrue();
    }

    @Test
    @DisplayName("Order의 주문일자가 주말이라면 False")
    void 주문일자_주말이_아닐_시_False() {
        //given
        int weekend = 21;
        Order order = new Order(orderItems, LocalDate.of(2023, 12, weekend));

        //when
        boolean expected = order.isWeekend();

        //then
        Assertions.assertThat(expected).isFalse();
    }

    @Test
    @DisplayName("Order의 주문일자가 평일이라면 True")
    void 주문일자_평일_True() {
        //given
        int weekday = 21;
        Order order = new Order(orderItems, LocalDate.of(2023, 12, weekday));

        //when
        boolean expected = order.isWeekday();

        //then
        Assertions.assertThat(expected).isTrue();
    }

    @Test
    @DisplayName("Order의 주문일자가 평일 아니라면 False")
    void 주문일자_평일_아닐_시_False() {
        //given
        int weekend = 22;
        Order order = new Order(orderItems, LocalDate.of(2023, 12, weekend));

        //when
        boolean expected = order.isWeekday();

        //then
        Assertions.assertThat(expected).isFalse();
    }

    @Test
    @DisplayName("Order의 총 주문 금액이 특정한 금액을 넘는다면 True")
    void 총_주문금액_일정금액_넘을_시_True() {
        //given
        Order order = new Order(orderItems, LocalDate.of(2023, 12, 21));

        //when
        boolean expected = order.isTotalAmountAboveThan(10000);

        //then
        Assertions.assertThat(expected).isTrue();
    }

    @Test
    @DisplayName("Order의 총 주문 금액이 특정한 금액을 넘지 못하면 False")
    void 총_주문금액_일정금액_넘지_못할_시_False() {
        //given
        Order order = new Order(orderItems, LocalDate.of(2023, 12, 21));

        //when
        boolean expected = order.isTotalAmountAboveThan(1200000);

        //then
        Assertions.assertThat(expected).isFalse();
    }

    @Test
    @DisplayName("Order의 특정 메뉴 타입이 몇개인지 확인하는 기능 테스트")
    void 특정_메뉴타입_개수_구하기_테스트() {

        //given
        int actual = 1;
        Order order = new Order(orderItems, LocalDate.of(2023, 12, 21));

        //when
        long expected = order.countMenuType(MenuType.DRINK);

        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Order가 특정 날짜들 사이에 있다면 True")
    void 특정날짜_사이_Ture() {
        //given
        Order order = new Order(orderItems, LocalDate.of(2023, 12, 21));
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

        //given
        Order order = new Order(orderItems, LocalDate.of(2023, 12, 21));
        LocalDate startDate = LocalDate.of(2023, 12, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 20);

        //when
        boolean expected = order.isTimeInRange(startDate, endDate);

        //then
        Assertions.assertThat(expected).isFalse();
    }


    @Test
    @DisplayName("Order의 주문일자가 특정날짜로부터 몇일지났는지 확인하는 기능 테스트")
    void 특정일자_부터_지난일수_구하기_기능_테스트() {
        //given
        int now = 22;
        int start = 1;
        Order order = new Order(orderItems, LocalDate.of(2023, 12, now));

        LocalDate startDate = LocalDate.of(2023, 12, start);

        int actual = now - start;

        //when
        long expected = order.calculateDaysSinceEventStart(startDate);

        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Order의 주문일자가 특정일자들에 포함된다면 True")
    void 특정일자들에_포함_Ture() {
        //given
        Order order = new Order(orderItems, LocalDate.of(2023, 12, 22));

        List<Integer> specialDays = List.of(3, 10, 22);

        //when
        boolean expected = order.isDateIncludedIn(specialDays);

        //then
        Assertions.assertThat(expected).isTrue();
    }

    @Test
    @DisplayName("Order의 주문일자가 특정일자들에 포함되지 않는다면 False")
    void 특정일자들에_포함_False() {
        //given
        Order order = new Order(orderItems, LocalDate.of(2023, 12, 22));

        List<Integer> days = List.of(3, 10);

        //when
        boolean expected = order.isDateIncludedIn(days);

        //then
        Assertions.assertThat(expected).isFalse();
    }

    @Test
    @DisplayName("Order 총 주문 금액을 확인")
    void Order_총_주문금액_테스트() {
        //given
        int actual = (mainMenu.getPrice() * mainMenuQuantity) + (drinkMenu.getPrice()
                * drinkMenuQuantity);
        Order order = new Order(orderItems, LocalDate.now());

        //when
        int expected = order.calculateTotalOrderAmount();

        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }


}