package christmas.model.order;

public class OrderQuantity {

    private int quantity;

    public OrderQuantity(int quantity) {
        validate(quantity);
        this.quantity = quantity;
    }

    private void validate(int quantity) {
        if(quantity < 1) {
            throw new IllegalArgumentException("[ERROR] 수량은 1개 이상 이어야 합니다");
        }
    }

    public int getQuantity() {
        return quantity;
    }



}
