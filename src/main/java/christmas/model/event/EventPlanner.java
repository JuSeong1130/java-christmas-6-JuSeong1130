package christmas.model.event;

import christmas.model.order.Order;
import java.util.ArrayList;
import java.util.List;

public class EventPlanner {
    private List<Event> events;

    public EventPlanner(List<Event> events) {
        this.events = events;
    }

    public DiscountResults calculateDiscountAmount(Order order) {
        List<DiscountResult> discountResults = new ArrayList<>();
        for (Event event : events) {
            discountResults.add(event.calculateDiscountAmount(order));
        }
        return new DiscountResults(discountResults);
    }
}
