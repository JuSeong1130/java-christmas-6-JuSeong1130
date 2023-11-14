package christmas.util;

import christmas.controller.dto.RequestOrderItemDto;
import christmas.exception.ConversionException;
import christmas.model.event.Badge;
import christmas.model.event.DiscountResult;
import christmas.model.event.DiscountResults;
import christmas.model.event.Gift;
import christmas.model.menu.Menu;
import christmas.model.menu.Menus;
import christmas.model.order.Order;
import christmas.model.order.OrderItem;
import christmas.model.order.OrderItems;
import christmas.model.order.OrderQuantity;
import christmas.service.DiscountResultDto;
import christmas.service.OrderListDto;
import christmas.service.OrderSummaryDto;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public static <T> List<T> fromStringToDto(String input, Class<T> targetType) {
        try {
            List<T> resultList = new ArrayList<>();
            String[] parts = input.split(",");

            for (String part : parts) {
                T dto = targetType.getDeclaredConstructor().newInstance();
                String[] keyValue = part.split("-");
                Field[] fields = targetType.getDeclaredFields();
                int numberOfFields = fields.length;

                for (int i = 0; i < numberOfFields; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);
                    Class<?> fieldType = field.getType();

                    if (fieldType == String.class) {
                        field.set(dto, keyValue[i].trim());
                    } else if (fieldType == int.class) {
                        field.set(dto, Integer.parseInt(keyValue[i].trim()));
                    } else if (fieldType == Double.class) {
                        field.set(dto, Double.parseDouble(keyValue[i].trim()));
                    }
                }
                resultList.add(dto);
            }

            return resultList;
        } catch (Exception e) {
            throw new ConversionException("[ERROR] 변환중 에러가 발생했습니다", e);
        }
    }

    public static OrderItems toOrderItems(List<RequestOrderItemDto> requestOrderItemDtos) {
        List<OrderItem> orderItems = new ArrayList<>();
        Menus menus = new Menus();
        for (RequestOrderItemDto orderItemDto : requestOrderItemDtos) {
            Menu menu = menus.findMenuBy(orderItemDto.getMenuName());
            OrderQuantity orderQuantity = new OrderQuantity(orderItemDto.getCount());
            orderItems.add(new OrderItem(menu, orderQuantity));
        }
        return new OrderItems(orderItems);
    }

    public static OrderSummaryDto toOrderSummaryDto(Order order, DiscountResults discountResults) {
        int totalPurchaseAmount = order.calculateTotalOrderAmount();
        int totalDiscountAmount = discountResults.calculateTotalDiscountAmount();
        String gift = Gift.findGiftNameBy(totalPurchaseAmount);
        String badge = Badge.findBadgeBy(totalDiscountAmount);
        List<OrderListDto> orderListDtos = toOrderListDtos(order);
        List<DiscountResultDto> discountResultDtos = toDiscountResultDtos(discountResults);
        return new OrderSummaryDto(orderListDtos, discountResultDtos, totalPurchaseAmount,
                totalDiscountAmount, gift, badge);
    }

    private static List<OrderListDto> toOrderListDtos(Order order) {
        List<OrderListDto> orderListDtos = new ArrayList<>();
        for (OrderItem orderItem : order.getOrderItems()) {
            orderListDtos.add(new OrderListDto(orderItem.getMenuName(), orderItem.getQuantity()));
        }
        return orderListDtos;
    }

    private static List<DiscountResultDto> toDiscountResultDtos(DiscountResults discountResults) {
        List<DiscountResultDto> discountResultDtos = new ArrayList<>();
        for (DiscountResult discountResult : discountResults.getDiscountResults()) {
            int discountAmount = discountResult.getDiscountAmount();
            if(discountAmount != 0) {
                discountResultDtos.add(new DiscountResultDto(discountResult.getEventName(),
                        discountAmount));
            }
        }
        return discountResultDtos;
    }
}
