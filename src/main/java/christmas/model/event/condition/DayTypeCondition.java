package christmas.model.event.condition;

import christmas.model.event.enums.DayType;
import christmas.model.order.Order;

public class DayTypeCondition implements EventCondition {

    private final DayType dayType;

    public DayTypeCondition(DayType dayType) {
        this.dayType = dayType;
    }

    @Override
    public boolean isValidCondition(Order order) {
        return order.isEqualDayType(dayType);
    }
}
