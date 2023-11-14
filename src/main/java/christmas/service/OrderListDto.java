package christmas.service;

public class OrderListDto {

    private String menuName;
    private int quantity;

    public OrderListDto(String menuName, int quantity) {
        this.menuName = menuName;
        this.quantity = quantity;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getQuantity() {
        return quantity;
    }
}
