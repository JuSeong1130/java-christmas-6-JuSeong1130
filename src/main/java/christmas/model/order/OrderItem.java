package christmas.model.order;

import christmas.model.menu.Menu;

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

}
