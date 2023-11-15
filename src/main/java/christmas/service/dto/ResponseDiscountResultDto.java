package christmas.service.dto;

public class ResponseDiscountResultDto {

    private final String eventName;
    private final int discountAmount;

    public ResponseDiscountResultDto(String eventName, int discountAmount) {
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
