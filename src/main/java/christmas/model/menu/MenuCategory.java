package christmas.model.menu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum MenuCategory {

    APPETIZER("에피타이저",AppetizerMenu.getAppetizerMenus()),
    MAIN_COURSE("메인", MainMenu.getMainMenus()),
    DESSERT("디저트", DessertMenu.getDessertMenus()),
    DRINK("음료", DrinkMenu.getDrinkMenus())
    ;
    private String categoryName;
    private List<Menu> menus;

    MenuCategory(String categoryName, List<Menu> menus) {
        this.categoryName = categoryName;
        this.menus = menus;
    }

    public static List<Menu> getAllMenus() {
        return Arrays.stream(MenuCategory.values())
                .flatMap(menuCategory -> menuCategory.menus.stream())
                .collect(Collectors.toList());
    }
    public static boolean isDrinkMenu(Menu menu) {
           return DRINK.menus.stream()
                    .anyMatch(drinkMenu -> drinkMenu.equals(menu));
    }
}
