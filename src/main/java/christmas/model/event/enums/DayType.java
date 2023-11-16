package christmas.model.event.enums;

public enum DayType {

    WEEKEND("WEEKEND"),
    WEEKDAY("WEEKDAY");
    private String name;

    DayType(String name) {
        this.name = name;
    }
}
