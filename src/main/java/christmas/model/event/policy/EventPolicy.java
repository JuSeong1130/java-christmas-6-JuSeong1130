package christmas.model.event.policy;

import christmas.model.order.Order;

public interface EventPolicy {

    int calculateDiscountAmount(Order order);
}
