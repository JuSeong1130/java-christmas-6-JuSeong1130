package christmas.model.order;

import static org.junit.jupiter.api.Assertions.*;

import christmas.model.menu.Menu;
import christmas.model.menu.MenuType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderQuantityTest {

    @Test
    @DisplayName("주문한 수량이 1개 미만이라면 예외 발생")
    void OrderItem_생성_예외_테스트() {
        // given
        int quantity = 0;
        Menu menu = new Menu(MenuType.DRINK, "테스트", 0);

        // when then
        Assertions.assertThatThrownBy(() -> new OrderItem(menu, new OrderQuantity(quantity)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("주문한 수량이 1개 이상이라면 예외 없이 생성 성공")
    void OrderItem_생성_성공_테스트() {
        // given
        int quantity = 1;
        Menu menu = new Menu(MenuType.DRINK, "테스트", 0);

        // when then
        Assertions.assertThatCode(() -> new OrderItem(menu, new OrderQuantity(quantity)))
                .doesNotThrowAnyException();
    }
}