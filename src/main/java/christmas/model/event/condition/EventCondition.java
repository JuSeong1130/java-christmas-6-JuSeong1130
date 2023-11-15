package christmas.model.event.condition;

import christmas.model.order.Order;

public interface EventCondition {

    boolean isValidCondition(Order order);
}
