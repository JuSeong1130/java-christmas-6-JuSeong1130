package christmas.model.event;

import christmas.model.event.enums.Gift;
import christmas.model.order.Order;
import java.time.LocalDate;

public class GiftEvent implements Event {

    private static final String GIFT_EVENT = "증정 이벤트";

    @Override
    public DiscountResult calculateDiscountAmount(Order order) {
        int discountAmount = 0;
        LocalDate startTime = LocalDate.of(2023, 12, 1);
        LocalDate endTime = LocalDate.of(2023, 12, 31);

        if (order.isTotalAmountAboveThan(120000) && order.isTimeInRange(startTime, endTime)) {
            discountAmount = Gift.findGiftAmountBy(order.calculateTotalOrderAmount());
        }
        return new DiscountResult(GIFT_EVENT, discountAmount);
    }
}
