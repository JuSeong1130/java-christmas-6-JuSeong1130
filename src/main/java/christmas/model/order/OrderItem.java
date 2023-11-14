package christmas.model.order;

import christmas.model.menu.Menu;
import christmas.model.menu.MenuType;
import java.util.Objects;

public class OrderItem {

    private Menu menu;
    private OrderQuantity orderQuantity;

    public OrderItem(Menu menu, OrderQuantity orderQuantity) {
        this.menu = menu;
        this.orderQuantity = orderQuantity;
    }

    public boolean isDrinkMenu() {
        return menu.isDrinkMenu();
    }

    public int calculateSubtotal() {
        return menu.getPrice() * getQuantity();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(menu, orderItem.menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu);
    }

    public boolean isEqualMenuType(MenuType menuType) {
        return menu.isEqualMenuType(menuType);
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return orderQuantity.getQuantity();
    }
}
