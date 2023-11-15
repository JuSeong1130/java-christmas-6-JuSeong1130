package christmas.model.event;

import christmas.model.event.condition.EventCondition;
import christmas.model.menu.MenuType;
import christmas.model.order.Order;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ChristmasEvent implements Event {

    private static final String CHRISTMAS_EVENT = "크리스마스 디데이 할인";

    private final LocalDate startDate;
    private final List<EventCondition> eventConditions;

    public ChristmasEvent(LocalDate startDate, List<EventCondition> eventConditions) {
        this.startDate = startDate;
        this.eventConditions = eventConditions;
    }

    @Override
    public DiscountResult calculateDiscountAmount(Order order) {
        int discountAmount = 0;
        if(isValidCondition(order)) {
            discountAmount = (order.calculateDaysSinceEventStart(startDate) * 100) + 1000;
        }
        return new DiscountResult(CHRISTMAS_EVENT, discountAmount);
    }

    private boolean isValidCondition(Order order) {
        return eventConditions.stream()
                .allMatch(eventCondition -> eventCondition.isValidCondition(order));
    }
}
