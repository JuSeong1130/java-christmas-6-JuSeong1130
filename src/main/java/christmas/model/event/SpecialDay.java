package christmas.model.event;

import java.util.Arrays;

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

    public static boolean hasDayOfMonth(int dayOfMonth) {
        return Arrays.stream(SpecialDay.values())
                .anyMatch(specialDay -> specialDay.dayOfMonth == dayOfMonth);
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }


}
