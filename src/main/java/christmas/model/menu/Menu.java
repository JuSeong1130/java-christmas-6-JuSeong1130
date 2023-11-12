package christmas.model.menu;

import java.util.Objects;

public class Menu {

    private String name;
    private int price;

    public Menu(String name, int price) {
        this.name = name;
        this.price = price;
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
        return price == menu.price && Objects.equals(name, menu.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }


}
