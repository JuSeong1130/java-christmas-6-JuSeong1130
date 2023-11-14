package christmas.view;

import christmas.io.Output;
import christmas.model.event.Badge;
import christmas.model.event.DiscountResult;
import christmas.model.event.Gift;
import christmas.model.order.Order;
import christmas.model.order.OrderItem;
import java.text.DecimalFormat;
import java.util.List;

public class OutputView {

    private Output output;

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
        output.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        output.println("");
    }

    public void printOrderInfo(Order order) {
        printEndMessage();
        printOrderList(order);
        printTotalAmountBeforeDiscount(order);
    }

    public void printTotalAmountBeforeDiscount(Order order) {
        output.println("<할인 전 총주문 금액>");
        output.printf("%,d원\n", order.totalPurchaseAmount());
    }


    public void printErrorMessage(String message) {
        output.println(message);
    }

    public void printOrderList(Order order) {
        output.println("<주문 메뉴>");
        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            output.printf("%s %d개\n", orderItem.getMenu().getName(), orderItem.getQuantity());
        }
        output.println("");
    }

    public void printDiscountResult(List<DiscountResult> discountResults, Order order) {
        printNewLine();
        printGift(order);
        printNewLine();
        printBenefits(discountResults);
        printNewLine();
        printTotalDiscountAmount(discountResults);
        printNewLine();
        printPaymentAmountAfterDiscount(discountResults, order);
        printNewLine();
        printBadge(discountResults);
    }

    private void printBadge(List<DiscountResult> discountResults) {
        output.println("<12월 이벤트 배지>");
        int discountAmount = 0;
        for (DiscountResult discountResult : discountResults) {
            discountAmount += (int) discountResult.getDiscountAmount();
        }
        output.println(Badge.findBadgeBy(discountAmount));
    }

    private void printPaymentAmountAfterDiscount(List<DiscountResult> discountResults, Order order) {
        output.println("<할인 후 예상 결제 금액>");
        int totalPurchaseAmount = order.totalPurchaseAmount();
        int discountAmount = 0;
        for (DiscountResult discountResult : discountResults) {
            discountAmount += (int) discountResult.getDiscountAmount();
        }
        output.printf("%,d원\n", totalPurchaseAmount - discountAmount);
    }

    private void printTotalDiscountAmount(List<DiscountResult> discountResults) {
        output.println("<총혜택 금액>");
        int result = 0;
        for (DiscountResult discountResult : discountResults) {
            result += (int) discountResult.getDiscountAmount();
        }
        String format = "-%,d원\n";
        if(result == 0) {
            format = "%,d원\n";
        }
        output.printf(format, result);
    }

    private void printBenefits(List<DiscountResult> discountResults) {
        output.println("<혜택 내역>");
        int result = 0;
        for (DiscountResult discountResult : discountResults) {
            result += (int) discountResult.getDiscountAmount();
        }
        if(result == 0) {
            output.println("없음");
            return;
        }

        for (DiscountResult discountResult : discountResults) {
            if (discountResult.getDiscountAmount() != 0) {
                output.printf("%s: -%,d원\n",discountResult.getEventName(), discountResult.getDiscountAmount());
            }
        }
    }

    private void printGift(Order order) {
        output.println("<증정 메뉴>");
        String name = Gift.findGiftNameBy(order.totalPurchaseAmount());
        output.println(name);
    }
}
