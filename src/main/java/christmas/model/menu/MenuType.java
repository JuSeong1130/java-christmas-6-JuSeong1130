package christmas.model.menu;

public enum MenuType {

    APPETIZER("에피타이저"),
    MAIN_COURSE("메인"),
    DESSERT("디저트"),
    DRINK("음료");
    private String categoryName;

    MenuType(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isDrinkMenu() {
        return this == DRINK;
    }
}
