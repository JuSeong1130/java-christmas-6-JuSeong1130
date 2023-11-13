package christmas.model.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Order {

    private List<OrderItem> orderItems;
    private LocalDateTime orderTime;

    public Order(List<OrderItem> orderItems, LocalDateTime orderTime) {
        validate(orderItems, orderTime);
        this.orderItems = orderItems;
        this.orderTime = orderTime;
    }

    private void validate(List<OrderItem> orderItems, LocalDateTime orderTime) {
        validateOrderQuantity(orderItems);
        validateDuplication(orderItems);
        validateOnlyDrinkMenu(orderItems);
    }

    //OrderItems?
    private void validateOrderQuantity(List<OrderItem> orderItems) {
        long totalQuantity = orderItems.stream()
                .mapToInt(OrderItem::getQuantity)
                .sum();
        if(totalQuantity > 20) {
            throw new IllegalArgumentException("[ERROR] 주문 수량은 20개 초과하면 안됩니다");
        }
    }

    private void validateDuplication(List<OrderItem> orderItems) {
        boolean hasDuplicationOrderItem = orderItems.stream()
                .distinct()
                .toList()
                .size() != orderItems.size();
        if (hasDuplicationOrderItem) {
            throw new IllegalArgumentException("[ERROR] 중복된 제품을 입력하면 안됩니다");
        }
    }

    private void validateOnlyDrinkMenu(List<OrderItem> orderItems) {

        boolean allDrinkMenu = orderItems.stream()
                .allMatch(OrderItem::isDrinkMenu);
        if (allDrinkMenu) {
            throw new IllegalArgumentException("[ERROR] 음료만 주문하시면 안됩니다");
        }

    }

    public int totalPurchaseAmount() {
        return orderItems.stream()
                .mapToInt(OrderItem::getAmount)
                .sum();
    }


    public boolean isWeekend() {
        return Day.isWeekend(String.valueOf(orderTime.getDayOfWeek()));
    }

    public boolean isTotalAmountAboveThan(int amount) {
        return totalPurchaseAmount() >= amount;
    }

    public long countMenuType(MenuType menuType) {
        return orderItems.stream()
                .filter(orderItem -> orderItem.isEqualMenuType(menuType))
                .mapToInt(OrderItem::getQuantity)
                .sum();
    }

    public boolean isTimeInRange(LocalDate startTime, LocalDate endTime) {
        return !orderTime.isBefore(startTime) && !orderTime.isAfter(endTime);
    }

}
