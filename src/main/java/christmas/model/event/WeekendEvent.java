package christmas.model.event;

import christmas.model.event.condition.EventCondition;
import christmas.model.menu.MenuType;
import christmas.model.order.Order;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class WeekendEvent implements Event {

    private static final String WEEKEND_EVENT = "주말 할인";

    private final List<EventCondition> eventConditions;

    public WeekendEvent(List<EventCondition> eventConditions) {
        this.eventConditions = eventConditions;
    }

    @Override
    public DiscountResult calculateDiscountAmount(Order order) {
        int discountAmount = 0;
        if(isValidCondition(order)) {
            discountAmount = order.countMenuType(MenuType.MAIN_COURSE) * 2023;
        }
        return new DiscountResult(WEEKEND_EVENT, discountAmount);
    }

    private boolean isValidCondition(Order order) {
        return eventConditions.stream()
                .allMatch(eventCondition -> eventCondition.isValidCondition(order));
    }
}
