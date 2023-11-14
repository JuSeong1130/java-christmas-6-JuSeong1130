package christmas.controller.dto;

public class RequestOrderItemDto {

    private String menuName;
    private int count;

    public RequestOrderItemDto() {
    }
    public RequestOrderItemDto(String menuName, int count) {
        this.menuName = menuName;
        this.count = count;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getCount() {
        return count;
    }
}
