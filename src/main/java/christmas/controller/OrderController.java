package christmas.controller;

import christmas.config.AppConfig;
import christmas.exception.InvalidFormatException;
import christmas.model.event.DiscountResult;
import christmas.model.event.EventPlanner;
import christmas.model.order.Order;
import christmas.model.order.OrderItem;
import christmas.util.OrderMapper;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.LocalDate;
import java.util.List;

public class OrderController {

    private InputView inputView;
    private OutputView outputView;

    public OrderController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Order order = processOrder();
        processEvent(order);
    }

    private Order processOrder() {
        printWelcome();
        LocalDate visitDate = createVisitDate();
        Order order = createOrder(visitDate);
        printOrderInfo(order);
        return order;
    }

    private void printWelcome() {
        outputView.printWelcome();
    }

    private LocalDate createVisitDate() {
        while (true) {
            try {
                return inputView.askVisitDate();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private Order createOrder(LocalDate localDate) {
        while (true) {
            try {
                List<OrderItem> orderItems = OrderMapper.toOrderItems(inputView.askOrder());
                return new Order(orderItems, localDate);
            } catch (IllegalArgumentException | InvalidFormatException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void printOrderInfo(Order order) {
        outputView.printOrderInfo(order);
    }

    private void processEvent(Order order) {
        EventPlanner eventPlanner = AppConfig.createEventPlanner();
        List<DiscountResult> discountResults = eventPlanner.calculateDiscountAmount(order);
        outputView.printDiscountResult(discountResults, order);
    }
}
