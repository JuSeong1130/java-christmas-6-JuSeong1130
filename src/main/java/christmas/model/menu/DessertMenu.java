package christmas.model.menu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum DessertMenu {

    CHOCOLATE_CAKE("초코케이크", 15000),
    ICECREAM("아이스크림", 5000)
    ;
    private final String menuName;
    private final int price;

    DessertMenu(String menuName, int price) {
        this.menuName = menuName;
        this.price = price;
    }

    public static List<Menu> getDessertMenus() {
        return Arrays.stream(DessertMenu.values())
                .map(dessertMenu -> new Menu(dessertMenu.menuName,dessertMenu.price))
                .collect(Collectors.toList());
    }

    public String getMenuName() {
        return menuName;
    }

    public int getPrice() {
        return price;
    }
}
