package christmas.model.menu;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MainMenuTest {

    @Test
    @DisplayName("메인메뉴가 Menu객체로 잘 변환 됬는지 테스트")
    void 메인메뉴_변환_테스트() {
        // given
        List<Menu> mainMenus = MainMenu.getMainMenus();
        // when, then
        for (MainMenu mainMenu : MainMenu.values()) {
            boolean hasMenu = mainMenus.stream()
                    .anyMatch(menu -> menu.equals(
                            new Menu(mainMenu.getMenuType(), mainMenu.getMenuName(),
                                    mainMenu.getPrice())));
            Assertions.assertThat(hasMenu).isTrue();
        }
    }

    @Test
    @DisplayName("메인메뉴 가격이 모두 0원 이상인지 테스트")
    void 메인메뉴_가격_테스트() {
        for (MainMenu mainMenu : MainMenu.values()) {
            boolean isGraterThanZero = mainMenu.getPrice() > 0;
            Assertions.assertThat(isGraterThanZero).isTrue();
        }
    }
}