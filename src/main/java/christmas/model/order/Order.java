package christmas.model.order;

import christmas.model.event.Day;
import christmas.model.menu.MenuType;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


public class Order {

    private OrderItems orderItems;
    private LocalDate orderTime;

    public Order(OrderItems orderItems, LocalDate orderTime) {
        this.orderItems = orderItems;
        this.orderTime = orderTime;
    }

    public int calculateTotalOrderAmount() {
        return orderItems.calculateTotalOrderAmount();
    }

    public boolean isWeekend() {
        return Day.isWeekend(String.valueOf(orderTime.getDayOfWeek()));
    }

    public boolean isTotalAmountAboveThan(int amount) {
        return calculateTotalOrderAmount() >= amount;
    }

    public int countMenuType(MenuType menuType) {
        return orderItems.countMenuType(menuType);
    }

    public boolean isTimeInRange(LocalDate startTime, LocalDate endTime) {
        return !orderTime.isBefore(startTime) && !orderTime.isAfter(endTime);
    }

    public boolean isWeekday() {
        return Day.isWeekday(String.valueOf(orderTime.getDayOfWeek()));
    }

    public int calculateDaysSinceEventStart(LocalDate startTime) {
        return (int) ChronoUnit.DAYS.between(startTime, orderTime);
    }

    public boolean isDateIncludedIn(List<Integer> days) {
        int dayOfMonth = orderTime.getDayOfMonth();
        return days.stream()
                .anyMatch(day -> day == dayOfMonth);
    }

    public List<OrderItem> getOrderItems() {
        return orderItems.getOrderItems();
    }
}
