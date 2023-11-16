package christmas.model.event;

import christmas.model.event.condition.EventCondition;
import christmas.model.event.enums.Gift;
import christmas.model.order.Order;
import java.time.LocalDate;
import java.util.List;

public class GiftEvent implements Event {

    private static final String GIFT_EVENT = "증정 이벤트";

    private final List<EventCondition> eventConditions;

    public GiftEvent(List<EventCondition> eventConditions) {
        this.eventConditions = eventConditions;
    }

    @Override
    public DiscountResult calculateDiscountAmount(Order order) {
        int discountAmount = 0;
        if (isValidCondition(order)) {
            discountAmount = Gift.findGiftAmountBy(order.calculateTotalOrderAmount());
        }
        return new DiscountResult(GIFT_EVENT, discountAmount);
    }

    private boolean isValidCondition(Order order) {
        return eventConditions.stream()
                .allMatch(eventCondition -> eventCondition.isValidCondition(order));
    }
}
