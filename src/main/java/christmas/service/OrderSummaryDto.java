package christmas.service;

import java.util.List;

public class OrderSummaryDto {

    private final List<OrderListDto> orderListDtos;

    private final List<DiscountResultDto> dicountResultDtos;

    private final int totalPurchaseAmount;
    private final int totalDiscountAmount;

    private final String gift;

    private final String badge;


    public OrderSummaryDto(List<OrderListDto> orderListDtos,
            List<DiscountResultDto> dicountResultDtos,
            int totalPurchaseAmount, int totalDiscountAmount, String gift, String badge) {
        this.orderListDtos = orderListDtos;
        this.dicountResultDtos = dicountResultDtos;
        this.totalPurchaseAmount = totalPurchaseAmount;
        this.totalDiscountAmount = totalDiscountAmount;
        this.gift = gift;
        this.badge = badge;
    }

    public List<OrderListDto> getOrderListDtos() {
        return orderListDtos;
    }

    public List<DiscountResultDto> getDicountResultDtos() {
        return dicountResultDtos;
    }

    public int getTotalPurchaseAmount() {
        return totalPurchaseAmount;
    }

    public int getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public String getGift() {
        return gift;
    }

    public String getBadge() {
        return badge;
    }

    public int getExpectedPurchaseAmountAfterDiscount() {
        return totalPurchaseAmount - totalDiscountAmount;
    }

}
