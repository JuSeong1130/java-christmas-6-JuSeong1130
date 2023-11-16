package christmas.model.event;

import christmas.model.order.Order;

public interface Event {

    DiscountResult calculateDiscountAmount(Order order);
}
