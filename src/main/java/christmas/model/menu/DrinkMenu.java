package christmas.model.menu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum DrinkMenu {

    ZERO_COLA("제로콜라", 3000),
    RED_WINE("레드와인", 60000),
    CHAMPAGNE("샴페인", 25000)
    ;

    private final String menuName;
    private final int price;

    DrinkMenu(String menuName, int price) {
        this.menuName = menuName;
        this.price = price;
    }

    public static List<Menu> getDrinkMenus() {
        return Arrays.stream(DrinkMenu.values())
                .map(drinkMenu -> new Menu(drinkMenu.menuName,drinkMenu.price))
                .collect(Collectors.toList());
    }

    public String getMenuName() {
        return menuName;
    }

    public int getPrice() {
        return price;
    }
}
