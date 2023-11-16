package christmas.model.event;

import christmas.model.event.condition.EventCondition;
import christmas.model.menu.MenuType;
import christmas.model.order.Order;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class WeekdayEvent implements Event {

    private static final String WEEKDAY_EVENT = "평일 할인";

    private final List<EventCondition> eventConditions;

    public WeekdayEvent(List<EventCondition> eventConditions) {
        this.eventConditions = eventConditions;
    }

    @Override
    public DiscountResult calculateDiscountAmount(Order order) {

        int discountAmount = 0;
        if (isValidCondition(order)) {
            discountAmount = order.countMenuType(MenuType.DESSERT) * 2023;
        }
        return new DiscountResult(WEEKDAY_EVENT, discountAmount);
    }

    private boolean isValidCondition(Order order) {
        return eventConditions.stream()
                .allMatch(eventCondition -> eventCondition.isValidCondition(order));
    }
}
