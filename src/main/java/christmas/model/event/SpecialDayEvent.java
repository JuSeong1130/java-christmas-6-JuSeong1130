package christmas.model.event;

import christmas.model.order.Order;
import java.time.LocalDate;
import java.util.List;

public class SpecialDayEvent implements Event {

    private static final String SPECIAL_EVENT = "특별 할인";

    @Override
    public DiscountResult calculateDiscountAmount(Order order) {
        long discountAmount = 0;
        LocalDate startTime = LocalDate.of(2023, 12, 1);
        LocalDate endTime = LocalDate.of(2023, 12, 25);
        List<Integer> days = SpecialDay.getDays();
        if(order.isDateIncludedIn(days) && order.isTotalAmountAboveThan(10000) && order.isTimeInRange(startTime, endTime)) {
            discountAmount = 1000;
        }
        return new DiscountResult(SPECIAL_EVENT, discountAmount);

    }
}
