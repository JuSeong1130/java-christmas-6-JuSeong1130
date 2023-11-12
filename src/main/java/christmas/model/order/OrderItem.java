package christmas.model.order;

import christmas.model.menu.Menu;
import christmas.model.menu.MenuCategory;
import java.util.Objects;

public class OrderItem {

    private Menu menu;
    private int quantity;

    public OrderItem(Menu menu, int quantity) {
        validate(menu, quantity);
        this.menu = menu;
        this.quantity = quantity;
    }

    private void validate(Menu menu, int quantity) {
        if(quantity < 1) {
            throw new IllegalArgumentException("[ERROR] 주문 제품은 수량은 1이상이어야 합니다");
        }
    }

    public boolean isDrinkMenu() {
        return MenuCategory.isDrinkMenu(menu);
    }

    public int getQuantity() {
        return quantity;
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
}
