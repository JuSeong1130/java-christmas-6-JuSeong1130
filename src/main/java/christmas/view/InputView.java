package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.dto.RequestOrderItemDto;
import christmas.io.Input;
import christmas.io.Output;
import christmas.util.InputValidator;
import christmas.util.OrderMapper;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

public class InputView {

    private Input input;
    private Output output;

    public InputView(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    public LocalDate askVisitDate() {
        try {
            output.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
            return LocalDate.of(2023, 12 , Integer.parseInt(input.readLine()));
        } catch (DateTimeException | NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public List<RequestOrderItemDto> askOrder() {
        output.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String menus = Console.readLine();
        InputValidator.validateMenu(menus);
        return OrderMapper.fromStringToDto(menus, RequestOrderItemDto.class);
    }
}
