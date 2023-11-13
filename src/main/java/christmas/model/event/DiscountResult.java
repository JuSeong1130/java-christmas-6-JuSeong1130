package christmas.model.event;

import java.util.Objects;

public class DiscountResult {

    private String eventName;
    private long discountAmount;

    public DiscountResult(String eventName, long discountAmount) {
        this.eventName = eventName;
        this.discountAmount = discountAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DiscountResult that = (DiscountResult) o;
        return discountAmount == that.discountAmount && Objects.equals(eventName,
                that.eventName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventName, discountAmount);
    }
}
