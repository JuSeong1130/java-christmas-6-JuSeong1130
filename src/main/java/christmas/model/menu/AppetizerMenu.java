package christmas.model.menu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum AppetizerMenu {

    MUSHROOM_SOUP("양송이수프", 6000),
    TAPAS("타파스", 5500),
    CAESAR_SALAD("시저샐러드", 8000);

    private static final MenuType menuType = MenuType.APPETIZER;
    private final String menuName;
    private final int price;

    AppetizerMenu(String menuName, int price) {
        this.menuName = menuName;
        this.price = price;
    }

    public static List<Menu> getAppetizerMenus() {
        return Arrays.stream(AppetizerMenu.values())
                .map(appetizerMenu -> new Menu(menuType, appetizerMenu.menuName,
                        appetizerMenu.price))
                .collect(Collectors.toList());
    }

    public String getMenuName() {
        return menuName;
    }

    public int getPrice() {
        return price;
    }

    public MenuType getMenuType() {
        return menuType;
    }
}
