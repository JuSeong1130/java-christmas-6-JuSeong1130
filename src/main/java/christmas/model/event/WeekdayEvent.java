package christmas.model.event;

import christmas.model.menu.MenuType;
import christmas.model.order.Order;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeekdayEvent implements Event {

    private static final String WEEKDAY_EVENT = "평일 할인";

    @Override
    public DiscountResult calculateDiscountAmount(Order order) {

        int discountAmount = 0;
        LocalDate startTime = LocalDate.of(2023, 12, 1);
        LocalDate endTime = LocalDate.of(2023, 12, 31);

        if (order.isWeekday() && order.isTotalAmountAboveThan(10000) && order.isTimeInRange(
                startTime, endTime)) {
            discountAmount = order.countMenuType(MenuType.DESSERT) * 2023;
        }
        return new DiscountResult(WEEKDAY_EVENT, discountAmount);
    }
}
