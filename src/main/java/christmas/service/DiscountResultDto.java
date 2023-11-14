package christmas.service;

public class DiscountResultDto {

    private final String eventName;
    private final int discountAmount;

    public DiscountResultDto(String eventName, int discountAmount) {
        this.eventName = eventName;
        this.discountAmount = discountAmount;
    }

    public String getEventName() {
        return eventName;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }
}
