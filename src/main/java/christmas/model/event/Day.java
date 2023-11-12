package christmas.model.event;

import java.util.Arrays;

public enum Day {
    MONDAY("weekday","MONDAY"),
    TUESDAY("weekday","TUESDAY"),
    WEDNESDAY("weekday","WEDNESDAY"),
    THURSDAY("weekday","THURSDAY"),
    FRIDAY("weekend", "FRIDAY"),
    SATURDAY("weekend", "SATURDAY"),
    SUNDAY("weekday","SUNDAY")
    ;

    private String dayType;
    private String dayOfTheWeek;

    Day(String dayType, String dayOfTheWeek) {
        this.dayType = dayType;
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public static boolean isWeekday(String dayOfTheWeek) {
        return Arrays.stream(Day.values())
                .filter(day -> day.dayOfTheWeek.equals(dayOfTheWeek))
                .anyMatch(day -> day.dayType.equals("weekday"));
    }

    public static boolean isWeekend(String dayOfTheWeek) {
        return !isWeekday(dayOfTheWeek);
    }
}
