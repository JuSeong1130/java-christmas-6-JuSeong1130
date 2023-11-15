package christmas.view;

import christmas.io.Output;
import christmas.service.dto.ResponseDiscountResultDto;
import christmas.service.dto.ResponseMenuDto;
import christmas.service.dto.ResponseMenuListDto;
import christmas.service.dto.ResponseOrderListDto;
import christmas.service.dto.ResponseOrderSummaryDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private Output output;

    private static final String ENTER = System.lineSeparator();

    public OutputView(Output output) {
        this.output = output;
    }


    private void printNewLine() {
        output.println("");
    }


    public void printStartMessage() {
        printWelcome();
        printCaution();
    }

    private void printWelcome() {
        output.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    private void printCaution() {
        output.println("""
                <주의 사항>
                총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.
                음료만 주문 시, 주문할 수 없습니다.
                메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.
                """);
    }

    public void printMenu(ResponseMenuListDto menus) {
        output.println("<메뉴 목록>");
        Map<String, List<ResponseMenuDto>> menuLists = menus.getMenuLists();
        for (String menuType : menuLists.keySet()) {
            output.printf("<%s>" + ENTER, menuType);
            output.println(getMenuMessage(menuLists.get(menuType)) + ENTER);
        }
    }

    private String getMenuMessage(List<ResponseMenuDto> menuDtos) {
        return menuDtos.stream()
                .map(responseMenuDto -> String.format("%s(%,d)", responseMenuDto.getName(),
                        responseMenuDto.getPrice()))
                .collect(Collectors.joining(", "));
    }

    public void printEndMessage() {
        output.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!" + ENTER);
    }

    public void printOrderSummary(ResponseOrderSummaryDto orderSummaryDto) {
        printOrderInfo(orderSummaryDto);
        printNewLine();
        printBenefitsResult(orderSummaryDto);
    }

    public void printOrderInfo(ResponseOrderSummaryDto orderSummaryDto) {
        printEndMessage();
        printOrderList(orderSummaryDto.getOrderListDtos());
        printTotalAmountBeforeDiscount(orderSummaryDto.getTotalPurchaseAmount());
    }

    public void printOrderList(List<ResponseOrderListDto> orderListDtos) {
        output.println("<주문 메뉴>");
        for (ResponseOrderListDto orderListDto : orderListDtos) {
            output.printf("%s %d개\n", orderListDto.getMenuName(), orderListDto.getQuantity());
        }
        output.println("");
    }

    public void printTotalAmountBeforeDiscount(int toTalOrderAmount) {
        output.println("<할인 전 총주문 금액>");
        output.printf("%,d원\n", toTalOrderAmount);
    }

    public void printBenefitsResult(ResponseOrderSummaryDto orderSummaryDto) {
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

    private void printBenefits(List<ResponseDiscountResultDto> discountResultDtos) {
        output.println("<혜택 내역>");
        if (discountResultDtos.size() == 0) {
            output.println("없음" + ENTER);
            return;
        }
        for (ResponseDiscountResultDto discountResultDto : discountResultDtos) {
            output.printf("%s: -%,d원\n", discountResultDto.getEventName(),
                    discountResultDto.getDiscountAmount());
        }
        output.println("");
    }

    private void printTotalDiscountAmount(int discountAmount) {
        output.println("<총혜택 금액>");
        String format = "-%,d원\n";
        if (discountAmount == 0) {
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
