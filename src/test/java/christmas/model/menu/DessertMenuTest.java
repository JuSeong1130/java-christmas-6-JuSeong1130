package christmas.model.menu;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DessertMenuTest {
    @Test
    @DisplayName("디저트가 Menu객체로 잘 변환 됬는지 테스트")
    void 디저트를_Menu객체로_변환_테스트() {
        // given
        List<Menu> appetizerMenus = DessertMenu.getDessertMenus();
        // when, then
        for(DessertMenu dessertMenu : DessertMenu.values()) {
            boolean hasMenu = appetizerMenus.stream()
                    .anyMatch(menu -> menu.isEqualName(dessertMenu.getMenuName())
                            && menu.isEqualPrice(dessertMenu.getPrice()));
            Assertions.assertThat(hasMenu).isTrue();
        }
    }

    @Test
    @DisplayName("디저트 가격이 모두 0원 이상인지 테스트")
    void 디저트_가격_테스트() {
        for(DessertMenu dessertMenu : DessertMenu.values()) {
            boolean isGraterThanZero = dessertMenu.getPrice() > 0;
            Assertions.assertThat(isGraterThanZero).isTrue();
        }
    }
}