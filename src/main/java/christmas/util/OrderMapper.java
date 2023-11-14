package christmas.util;

import christmas.controller.dto.RequestOrderItemDto;
import christmas.exception.ConversionException;
import christmas.model.menu.Menu;
import christmas.model.menu.Menus;
import christmas.model.order.OrderItem;
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

    public static List<OrderItem> toOrderItems(List<RequestOrderItemDto> requestOrderItemDtos) {
        List<OrderItem> orderItems = new ArrayList<>();
        Menus menus = new Menus();
        for (RequestOrderItemDto orderItemDto : requestOrderItemDtos) {
            Menu menu = menus.findMenuBy(orderItemDto.getMenuName());
            orderItems.add(new OrderItem(menu, orderItemDto.getCount()));
        }
        return orderItems;
    }
}
