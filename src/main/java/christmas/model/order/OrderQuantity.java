package christmas.model.order;

public class OrderQuantity {

    private int quantity;

    public OrderQuantity(int quantity) {
        validate(quantity);
        this.quantity = quantity;
    }

    private void validate(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public int getQuantity() {
        return quantity;
    }


}
