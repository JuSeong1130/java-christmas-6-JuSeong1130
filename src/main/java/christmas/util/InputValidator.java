package christmas.util;

import christmas.exception.InvalidFormatException;
import java.util.regex.Pattern;

public class InputValidator {

    public static void validateMenu(String menu) {
        String regex = "([A-Za-z가-힣])+-\\d(,([A-Za-z가-힣])+-\\d+)*";  //문자-숫자 형식은 무조건 와야 하며 ,문자-숫자 형식은 올 수도 있고 안 올 수도 있다는 정규식
        if(!Pattern.matches(regex, menu)) {
            throw new InvalidFormatException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
