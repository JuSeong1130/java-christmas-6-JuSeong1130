package christmas.service;

import christmas.config.AppConfig;
import christmas.controller.dto.RequestOrderItemDto;
import christmas.model.event.DiscountResult;
import christmas.model.event.DiscountResults;
import christmas.model.event.EventPlanner;
import christmas.model.order.Order;
import christmas.model.order.OrderItems;
import christmas.util.OrderMapper;
import java.time.LocalDate;
import java.util.List;

public class OrderService {

    public OrderSummaryDto calculateExpectedOrder(LocalDate selectedDate,
            List<RequestOrderItemDto> orderItemDtos) {
        OrderItems orderItems = OrderMapper.toOrderItems(orderItemDtos);
        Order order = new Order(orderItems, selectedDate);

        EventPlanner eventPlanner = AppConfig.createEventPlanner();
        DiscountResults discountResults = eventPlanner.calculateDiscountAmount(order);

        return OrderMapper.toOrderSummaryDto(order, discountResults);
    }
}
