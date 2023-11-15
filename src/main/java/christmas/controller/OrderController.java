package christmas.controller;

import christmas.controller.dto.RequestOrderItemDto;
import christmas.service.OrderService;
import christmas.service.dto.ResponseOrderSummaryDto;
import christmas.util.ExceptionRetryHandler;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.LocalDate;
import java.util.List;

public class OrderController {

    private final InputView inputView;
    private final OutputView outputView;

    private final OrderService orderService;

    public OrderController(InputView inputView, OutputView outputView, OrderService orderService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.orderService = orderService;
    }

    public void begin() {
        outputView.printStartMessage();
        ResponseOrderSummaryDto orderSummaryDto = calculateExpectedOrder();
        outputView.printOrderSummary(orderSummaryDto);
    }

    private ResponseOrderSummaryDto calculateExpectedOrder() {
        LocalDate selectedDate = ExceptionRetryHandler.retryUntilSuccess(inputView::askVisitDate);
        outputView.printMenu(orderService.findMenus());
        List<RequestOrderItemDto> requestOrderItemDtos = ExceptionRetryHandler.retryUntilSuccess(
                inputView::askOrder);
        return ExceptionRetryHandler.retryUntilSuccess(orderService::calculateExpectedOrder,
                selectedDate, requestOrderItemDtos);
    }
}
