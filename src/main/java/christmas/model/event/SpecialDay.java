package christmas.model.event;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum SpecialDay {

    DAY_3(3),
    DAY_10(10),
    DAY_17(17),
    DAY_24(24),
    DAY_26(26),
    DAY_31(31);
    ;

    private final int dayOfMonth;

    SpecialDay(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public static List<Integer> getDays() {
        return Arrays.stream(SpecialDay.values())
                .map(SpecialDay::getDayOfMonth)
                .collect(Collectors.toList());
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }


}
