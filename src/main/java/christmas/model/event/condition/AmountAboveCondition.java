package christmas.model.event.condition;

import christmas.model.order.Order;

public class AmountAboveCondition implements EventCondition {

    private final int minimumAmount;

    public AmountAboveCondition(int minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    @Override
    public boolean isValidCondition(Order order) {
        return order.isTotalAmountAboveThan(minimumAmount);
    }
}
