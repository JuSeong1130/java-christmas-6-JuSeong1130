package christmas.model.event;

import christmas.model.menu.MenuType;
import christmas.model.order.Order;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ChristmasEvent implements Event {

    private static final String CHRISTMAS_EVENT = "크리스마스 디데이 할인";

    @Override
    public DiscountResult calculateDiscountAmount(Order order) {
        int discountAmount = 0;

        LocalDate startTime = LocalDate.of(2023, 12, 1);
        LocalDate endTime = LocalDate.of(2023, 12, 25);

        if (order.isTotalAmountAboveThan(10000) && order.isTimeInRange(startTime, endTime)) {
            discountAmount = (order.calculateDaysSinceEventStart(startTime) * 100) + 1000;
        }
        return new DiscountResult(CHRISTMAS_EVENT, discountAmount);
    }
}
