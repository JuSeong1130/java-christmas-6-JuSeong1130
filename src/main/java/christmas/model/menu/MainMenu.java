package christmas.model.menu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum MainMenu {

    T_BONE_STEAK("티본스테이크", 55000),
    BARBECUE_RIBS("바베큐립", 54000),
    SEAFOOD_PASTA("해산물파스타", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", 25000)
    ;

    private final String menuName;
    private final int price;

    MainMenu(String menuName, int price) {
        this.menuName = menuName;
        this.price = price;
    }
    public static List<Menu> getMainMenus() {
        return Arrays.stream(MainMenu.values())
                .map(mainMenu -> new Menu(mainMenu.menuName,mainMenu.price))
                .collect(Collectors.toList());
    }

    public String getMenuName() {
        return menuName;
    }

    public int getPrice() {
        return price;
    }
}
