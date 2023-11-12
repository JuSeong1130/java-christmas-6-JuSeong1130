package christmas.model.menu;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

class AppetizerMenuTest {
    
    @Test
    @DisplayName("에피타이저가 Menu객체로 잘 변환 됬는지 테스트")
    void 에피타이저를_Menu객체로_변환_테스트() {
        // given
        List<Menu> appetizerMenus = AppetizerMenu.getAppetizerMenus();
        // when, then
        for(AppetizerMenu appetizerMenu : AppetizerMenu.values()) {
            boolean hasMenu = appetizerMenus.stream()
                    .anyMatch(menu -> menu.isEqualName(appetizerMenu.getMenuName())
                            && menu.isEqualPrice(appetizerMenu.getPrice()));
            Assertions.assertThat(hasMenu).isTrue();
        }
    }

    @Test
    @DisplayName("에피타이저 가격이 모두 0원 이상인지 테스트")
    void 에피타이저_가격_테스트() {
        for(AppetizerMenu appetizerMenu : AppetizerMenu.values()) {
            boolean isGraterThanZero = appetizerMenu.getPrice() > 0;
            Assertions.assertThat(isGraterThanZero).isTrue();
        }
    }
}