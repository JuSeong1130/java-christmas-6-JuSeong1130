package christmas.model.event;

import christmas.model.menu.MenuType;
import christmas.model.order.Order;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeekendEvent implements Event{

    private static final String WEEKEND_EVENT = "주말 할인";

    @Override
    public DiscountResult calculateDiscountAmount(Order order) {
        long discountAmount = 0;

        LocalDate startTime = LocalDate.of(2023, 12, 1);
        LocalDate endTime = LocalDate.of(2023, 12, 31);

        if(order.isWeekend() && order.isTotalAmountAboveThan(10000) && order.isTimeInRange(startTime, endTime)) {
            discountAmount = order.countMenuType(MenuType.MAIN_COURSE) * 2023;
        }
        return new DiscountResult(WEEKEND_EVENT, discountAmount);
    }
}
