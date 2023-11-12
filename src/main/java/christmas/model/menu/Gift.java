package christmas.model.menu;

public enum Gift {

    CHAMPAGNE(DrinkMenu.CHAMPAGNE.getMenuName(), DrinkMenu.CHAMPAGNE.getPrice());
    private String name;
    private int price;

    Gift(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
