package christmas.service.dto;

public class ResponseOrderListDto {

    private String menuName;
    private int quantity;

    public ResponseOrderListDto(String menuName, int quantity) {
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
