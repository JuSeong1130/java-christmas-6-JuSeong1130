package christmas.model.order;

import christmas.model.menu.MenuType;
import java.time.LocalDate;
import java.util.List;

public class OrderItems {

    private List<OrderItem> orderItems;

    public OrderItems(List<OrderItem> orderItems) {
        validate(orderItems);
        this.orderItems = orderItems;
    }

    private void validate(List<OrderItem> orderItems) {
        validateOrderQuantity(orderItems);
        validateDuplication(orderItems);
        validateOnlyDrinkMenu(orderItems);
    }

    private void validateOrderQuantity(List<OrderItem> orderItems) {
        long totalQuantity = orderItems.stream()
                .mapToInt(OrderItem::getQuantity)
                .sum();
        if (totalQuantity > 20) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateDuplication(List<OrderItem> orderItems) {
        boolean hasDuplicationOrderItem = orderItems.stream()
                .distinct()
                .toList()
                .size() != orderItems.size();
        if (hasDuplicationOrderItem) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateOnlyDrinkMenu(List<OrderItem> orderItems) {
        boolean allDrinkMenu = orderItems.stream()
                .allMatch(OrderItem::isDrinkMenu);
        if (allDrinkMenu) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }

    }

    public int calculateTotalOrderAmount() {
        return orderItems.stream()
                .mapToInt(OrderItem::calculateSubtotal)
                .sum();
    }

    public int countMenuType(MenuType menuType) {
        return orderItems.stream()
                .filter(orderItem -> orderItem.isEqualMenuType(menuType))
                .mapToInt(OrderItem::getQuantity)
                .sum();
    }


    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
}
