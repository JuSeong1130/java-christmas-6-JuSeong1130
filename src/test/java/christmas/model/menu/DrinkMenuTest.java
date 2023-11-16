package christmas.model.menu;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DrinkMenuTest {

    @Test
    @DisplayName("음료가 Menu객체로 잘 변환 됬는지 테스트")
    void 음료_변환_테스트() {
        // given
        List<Menu> drinkMenus = DrinkMenu.getDrinkMenus();
        // when, then
        for (DrinkMenu drinkMenu : DrinkMenu.values()) {
            boolean hasMenu = drinkMenus.stream()
                    .anyMatch(menu -> menu.equals(
                            new Menu(drinkMenu.getMenuType(), drinkMenu.getMenuName(),
                                    drinkMenu.getPrice())));
            Assertions.assertThat(hasMenu).isTrue();
        }
    }

    @Test
    @DisplayName("음료 가격이 모두 0원 이상인지 테스트")
    void 음료_가격_테스트() {
        for (DrinkMenu drinkMenu : DrinkMenu.values()) {
            boolean isGraterThanZero = drinkMenu.getPrice() > 0;
            Assertions.assertThat(isGraterThanZero).isTrue();
        }
    }
}