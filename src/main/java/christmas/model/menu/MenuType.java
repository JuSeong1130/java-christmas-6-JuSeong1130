package christmas.model.menu;

public enum MenuType {

    APPETIZER("에피타이저"),
    MAIN_COURSE("메인"),
    DESSERT("디저트"),
    DRINK("음료");
    private String typeName;

    MenuType(String typeName) {
        this.typeName = typeName;
    }

    public boolean isDrinkMenu() {
        return this == DRINK;
    }

    public String getTypeName() {
        return typeName;
    }
}
