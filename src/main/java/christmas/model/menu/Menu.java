package christmas.model.menu;

import java.util.Objects;

public class Menu {

    private MenuType menuType;
    private String name;
    private int price;

    public Menu(MenuType menuType, String name, int price) {
        this.menuType = menuType;
        this.name = name;
        this.price = price;
    }

    public boolean isEqualMenuType(MenuType menuType) {
        return this.menuType == menuType;
    }

    public boolean isEqualName(String menuName) {
        return Objects.equals(this.name, menuName);
    }

    public boolean isEqualPrice(int price) {
        return this.price == price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Menu menu = (Menu) o;
        return price == menu.price && menuType == menu.menuType && Objects.equals(name,
                menu.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuType, name, price);
    }

    public int getPrice() {
        return price;
    }

    public boolean isDrinkMenu() {
        return menuType.isDrinkMenu();
    }

    public String getName() {
        return name;
    }
}
