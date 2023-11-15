package christmas.model.event;

import christmas.model.event.condition.EventCondition;
import christmas.model.event.enums.SpecialDay;
import christmas.model.order.Order;
import java.time.LocalDate;
import java.util.List;

public class SpecialDayEvent implements Event {

    private static final String SPECIAL_EVENT = "특별 할인";

    private final List<EventCondition> eventConditions;

    public SpecialDayEvent(List<EventCondition> eventConditions) {
        this.eventConditions = eventConditions;
    }

    @Override
    public DiscountResult calculateDiscountAmount(Order order) {
        int discountAmount = 0;
        if (isValidCondition(order)) {
            discountAmount = 1000;
        }
        return new DiscountResult(SPECIAL_EVENT, discountAmount);

    }

    private boolean isValidCondition(Order order) {
        return eventConditions.stream()
                .allMatch(eventCondition -> eventCondition.isValidCondition(order));
    }
}
