package christmas.service;

import christmas.config.AppConfig;
import christmas.controller.dto.RequestOrderItemDto;
import christmas.model.event.DiscountResults;
import christmas.model.event.EventPlanner;
import christmas.model.menu.Menus;
import christmas.model.order.Order;
import christmas.model.order.OrderItems;
import christmas.service.dto.ResponseMenuListDto;
import christmas.service.dto.ResponseOrderSummaryDto;
import christmas.util.OrderMapper;
import java.time.LocalDate;
import java.util.List;

public class OrderService {


    public ResponseMenuListDto findMenus() {
        return OrderMapper.toResponseMenuListDto(new Menus().getMenus());
    }

    public ResponseOrderSummaryDto calculateExpectedOrder(LocalDate selectedDate,
            List<RequestOrderItemDto> orderItemDtos) {
        OrderItems orderItems = OrderMapper.toOrderItems(orderItemDtos);
        Order order = new Order(orderItems, selectedDate);

        EventPlanner eventPlanner = AppConfig.createEventPlanner();
        DiscountResults discountResults = eventPlanner.calculateDiscountAmount(order);

        return OrderMapper.toOrderSummaryDto(order, discountResults);
    }
}
