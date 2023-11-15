package christmas.model.event.condition;

import christmas.model.order.Order;
import java.util.List;

public class SpecificDateCondition implements EventCondition {

    private final List<Integer> days;

    public SpecificDateCondition(List<Integer> days) {
        this.days = days;
    }

    @Override
    public boolean isValidCondition(Order order) {
        return order.isDateIncludedIn(days);
    }
}
