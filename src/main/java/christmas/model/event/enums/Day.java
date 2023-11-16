package christmas.model.event.enums;

import java.time.DayOfWeek;
import java.util.Arrays;

public enum Day {
    MONDAY(DayType.WEEKDAY, "MONDAY"),
    TUESDAY(DayType.WEEKDAY, "TUESDAY"),
    WEDNESDAY(DayType.WEEKDAY, "WEDNESDAY"),
    THURSDAY(DayType.WEEKDAY, "THURSDAY"),
    FRIDAY(DayType.WEEKEND, "FRIDAY"),
    SATURDAY(DayType.WEEKEND, "SATURDAY"),
    SUNDAY(DayType.WEEKDAY, "SUNDAY");

    private DayType dayType;
    private String dayOfTheWeek;

    Day(DayType dayType, String dayOfTheWeek) {
        this.dayType = dayType;
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public static boolean isEqualDayType(String dayOfTheWeek, DayType dayType) {
        return Arrays.stream(Day.values())
                .filter(day -> day.dayOfTheWeek.equals(dayOfTheWeek))
                .anyMatch(day -> day.dayType == dayType);
    }
}
