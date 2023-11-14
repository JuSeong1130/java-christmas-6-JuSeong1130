package christmas.controller;

import christmas.config.AppConfig;
import christmas.controller.dto.RequestOrderItemDto;
import christmas.exception.InvalidFormatException;
import christmas.model.event.DiscountResult;
import christmas.model.event.EventPlanner;
import christmas.model.order.Order;
import christmas.model.order.OrderItem;
import christmas.model.order.OrderItems;
import christmas.service.OrderService;
import christmas.service.OrderSummaryDto;
import christmas.util.ExceptionRetryHandler;
import christmas.util.OrderMapper;
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

    public void beginExpectedOrderProcessing() {
        outputView.printWelcome();
        OrderSummaryDto orderSummaryDto = calculateExpectedOrder();
        outputView.printOrderSummary(orderSummaryDto);
    }

    private OrderSummaryDto calculateExpectedOrder() {
        LocalDate selectedDate = ExceptionRetryHandler.retryUntilSuccess(inputView::askVisitDate);
        List<RequestOrderItemDto> requestOrderItemDtos = ExceptionRetryHandler.retryUntilSuccess(
                inputView::askOrder);
        return ExceptionRetryHandler.retryUntilSuccess(orderService::calculateExpectedOrder, selectedDate, requestOrderItemDtos);
    }
}
