package christmas.model.menu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Menus {

    private List<Menu> menus;

    public Menus() {
        this.menus = initMenus();
    }

    private List<Menu> initMenus() {
        List<Menu> appetizerMenus = AppetizerMenu.getAppetizerMenus();
        List<Menu> dessertMenus = DessertMenu.getDessertMenus();
        List<Menu> drinkMenus = DrinkMenu.getDrinkMenus();
        List<Menu> mainMenus = MainMenu.getMainMenus();
        return Stream.of(appetizerMenus,dessertMenus, drinkMenus, mainMenus)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public Menu findMenuBy(String menuName) {
        return menus.stream()
                .filter(menu -> menu.isEqualName(menuName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."));
    }
}
