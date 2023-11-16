package christmas.util;

import christmas.controller.dto.RequestOrderItemDto;
import christmas.exception.ConversionException;
import christmas.model.event.enums.Badge;
import christmas.model.event.DiscountResult;
import christmas.model.event.DiscountResults;
import christmas.model.event.enums.Gift;
import christmas.model.menu.Menu;
import christmas.model.menu.Menus;
import christmas.model.order.Order;
import christmas.model.order.OrderItem;
import christmas.model.order.OrderItems;
import christmas.model.order.OrderQuantity;
import christmas.service.dto.ResponseDiscountResultDto;
import christmas.service.dto.ResponseMenuDto;
import christmas.service.dto.ResponseMenuListDto;
import christmas.service.dto.ResponseOrderListDto;
import christmas.service.dto.ResponseOrderSummaryDto;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
            throw new ConversionException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.", e);
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

    public static ResponseOrderSummaryDto toOrderSummaryDto(Order order,
            DiscountResults discountResults) {
        int totalPurchaseAmount = order.calculateTotalOrderAmount();
        int totalDiscountAmount = discountResults.calculateTotalDiscountAmount();
        String gift = Gift.findGiftNameBy(totalPurchaseAmount);
        String badge = Badge.findBadgeBy(totalDiscountAmount);
        List<ResponseOrderListDto> orderListDtos = toOrderListDtos(order);
        List<ResponseDiscountResultDto> discountResultDtos = toDiscountResultDtos(discountResults);
        return new ResponseOrderSummaryDto(orderListDtos, discountResultDtos, totalPurchaseAmount,
                totalDiscountAmount, gift, badge);
    }

    private static List<ResponseOrderListDto> toOrderListDtos(Order order) {
        List<ResponseOrderListDto> orderListDtos = new ArrayList<>();
        for (OrderItem orderItem : order.getOrderItems()) {
            orderListDtos.add(
                    new ResponseOrderListDto(orderItem.getMenuName(), orderItem.getQuantity()));
        }
        return orderListDtos;
    }

    private static List<ResponseDiscountResultDto> toDiscountResultDtos(
            DiscountResults discountResults) {
        List<ResponseDiscountResultDto> discountResultDtos = new ArrayList<>();
        for (DiscountResult discountResult : discountResults.getDiscountResults()) {
            int discountAmount = discountResult.getDiscountAmount();
            if (discountAmount != 0) {
                discountResultDtos.add(new ResponseDiscountResultDto(discountResult.getEventName(),
                        discountAmount));
            }
        }
        return discountResultDtos;
    }

    public static ResponseMenuListDto toResponseMenuListDto(List<Menu> menus) {
        Map<String, List<ResponseMenuDto>> menuLists = menus.stream()
                .collect(Collectors.groupingBy(Menu::getMenuType,
                        Collectors.mapping(
                                menu -> new ResponseMenuDto(menu.getName(), menu.getPrice()),
                                Collectors.toList())));
        return new ResponseMenuListDto(menuLists);
    }
}
