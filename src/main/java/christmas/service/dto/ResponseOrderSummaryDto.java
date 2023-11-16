package christmas.service.dto;

import christmas.service.dto.ResponseDiscountResultDto;
import christmas.service.dto.ResponseOrderListDto;
import java.util.List;

public class ResponseOrderSummaryDto {

    private final List<ResponseOrderListDto> orderListDtos;

    private final List<ResponseDiscountResultDto> dicountResultDtos;

    private final int totalPurchaseAmount;
    private final int totalDiscountAmount;

    private final String gift;

    private final String badge;


    public ResponseOrderSummaryDto(List<ResponseOrderListDto> orderListDtos,
            List<ResponseDiscountResultDto> dicountResultDtos,
            int totalPurchaseAmount, int totalDiscountAmount, String gift, String badge) {
        this.orderListDtos = orderListDtos;
        this.dicountResultDtos = dicountResultDtos;
        this.totalPurchaseAmount = totalPurchaseAmount;
        this.totalDiscountAmount = totalDiscountAmount;
        this.gift = gift;
        this.badge = badge;
    }

    public List<ResponseOrderListDto> getOrderListDtos() {
        return orderListDtos;
    }

    public List<ResponseDiscountResultDto> getDicountResultDtos() {
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
