package christmas.model.menu;

import java.util.List;

public class Menus {

    private List<Menu> menus;

    public Menus() {
        this.menus = MenuCategory.getAllMenus();
    }

    public Menu findMenuBy(String menuName) {
        return menus.stream()
                .filter(menu -> menu.isEqualName(menuName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 올바른 메뉴를 입력해주세요"));
    }
}
