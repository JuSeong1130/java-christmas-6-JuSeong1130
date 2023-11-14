package christmas.view;

import christmas.io.Output;
import christmas.service.DiscountResultDto;
import christmas.service.OrderListDto;
import christmas.service.OrderSummaryDto;
import java.util.List;

public class OutputView {

    private Output output;

    private static final String ENTER = System.lineSeparator();

    public OutputView(Output output) {
        this.output = output;
    }


    private void printNewLine() {
        output.println("");
    }

    public void printWelcome() {
        output.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printEndMessage() {
        output.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!" + ENTER);
    }

    public void printOrderSummary(OrderSummaryDto orderSummaryDto) {
        printOrderInfo(orderSummaryDto);
        printNewLine();
        printBenefitsResult(orderSummaryDto);
    }

    public void printOrderInfo(OrderSummaryDto orderSummaryDto) {
        printEndMessage();
        printOrderList(orderSummaryDto.getOrderListDtos());
        printTotalAmountBeforeDiscount(orderSummaryDto.getTotalPurchaseAmount());
    }

    public void printOrderList(List<OrderListDto> orderListDtos) {
        output.println("<주문 메뉴>");
        for (OrderListDto orderListDto : orderListDtos) {
            output.printf("%s %d개\n", orderListDto.getMenuName(), orderListDto.getQuantity());
        }
        output.println("");
    }

    public void printTotalAmountBeforeDiscount(int toTalOrderAmount) {
        output.println("<할인 전 총주문 금액>");
        output.printf("%,d원\n", toTalOrderAmount);
    }
    public void printBenefitsResult(OrderSummaryDto orderSummaryDto) {
        printGift(orderSummaryDto.getGift());
        printBenefits(orderSummaryDto.getDicountResultDtos());
        printTotalDiscountAmount(orderSummaryDto.getTotalDiscountAmount());
        printPaymentAmountAfterDiscount(orderSummaryDto.getExpectedPurchaseAmountAfterDiscount());
        printBadge(orderSummaryDto.getBadge());
    }
    private void printGift(String name) {
        output.println("<증정 메뉴>");
        output.println(name + ENTER);
    }

    private void printBenefits(List<DiscountResultDto> discountResultDtos) {
        output.println("<혜택 내역>");
        if(discountResultDtos.size() == 0) {
            output.println("없음" + ENTER);
            return;
        }
        for (DiscountResultDto discountResultDto : discountResultDtos) {
            output.printf("%s: -%,d원\n",discountResultDto.getEventName(), discountResultDto.getDiscountAmount());
        }
        output.println("");
    }

    private void printTotalDiscountAmount(int discountAmount) {
        output.println("<총혜택 금액>");
        String format = "-%,d원\n";
        if(discountAmount == 0) {
            format = "%,d원\n";
        }
        output.printf(format + ENTER, discountAmount);
    }

    private void printPaymentAmountAfterDiscount(int expecteAmount) {
        output.println("<할인 후 예상 결제 금액>");
        output.printf("%,d원\n" + ENTER, expecteAmount);
    }

    private void printBadge(String badge) {
        output.println("<12월 이벤트 배지>");
        output.println(badge);
    }

    public void printErrorMessage(String message) {
        output.println(message);
    }

}
