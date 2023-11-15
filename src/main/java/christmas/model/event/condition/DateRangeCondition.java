package christmas.model.event.condition;

import christmas.model.order.Order;
import java.time.LocalDate;

public class DateRangeCondition implements EventCondition {

    private final LocalDate startDate;
    private final LocalDate endDate;

    public DateRangeCondition(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean isValidCondition(Order order) {
        return order.isTimeInRange(startDate, endDate);
    }
}
