package christmas.config;

import christmas.controller.OrderController;
import christmas.io.ConsoleInput;
import christmas.io.Input;
import christmas.io.Output;
import christmas.io.SystemOutput;
import christmas.model.event.ChristmasEvent;
import christmas.model.event.Event;
import christmas.model.event.EventPlanner;
import christmas.model.event.GiftEvent;
import christmas.model.event.SpecialDayEvent;
import christmas.model.event.WeekdayEvent;
import christmas.model.event.WeekendEvent;
import christmas.model.event.condition.AmountAboveCondition;
import christmas.model.event.condition.DateRangeCondition;
import christmas.model.event.condition.DayTypeCondition;
import christmas.model.event.condition.EventCondition;
import christmas.model.event.condition.SpecificDateCondition;
import christmas.model.event.enums.DayType;
import christmas.model.event.enums.SpecialDay;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.LocalDate;
import java.util.List;

public class AppConfig {

    public static EventPlanner createEventPlanner() {
        List<Event> events = List.of(createChristmasEvent(), createGiftEvent(),
                createWeekendEvent(),
                createWeekdayEvent(), createSpecialDayEvent());
        return new EventPlanner(events);
    }

    public static Event createChristmasEvent() {
        LocalDate startDate = LocalDate.of(2023, 12, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 25);
        int minimumAmount = 10000;

        List<EventCondition> eventConditions = List.of(
                createAmountAboveCondition(minimumAmount),
                createDateRangeCondition(startDate, endDate));

        return new ChristmasEvent(startDate, eventConditions);
    }

    public static Event createGiftEvent() {
        LocalDate startDate = LocalDate.of(2023, 12, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        int minimumAmount = 120000;
        List<EventCondition> eventConditions = List.of(
                createAmountAboveCondition(minimumAmount),
                createDateRangeCondition(startDate, endDate));
        return new GiftEvent(eventConditions);
    }

    public static Event createWeekdayEvent() {
        LocalDate startDate = LocalDate.of(2023, 12, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        int minimumAmount = 10000;
        DayType dayType = DayType.WEEKDAY;
        List<EventCondition> eventConditions = List.of(
                createAmountAboveCondition(minimumAmount),
                createDateRangeCondition(startDate, endDate),
                createDayTypeCondition(dayType));
        return new WeekdayEvent(eventConditions);
    }

    public static Event createWeekendEvent() {
        LocalDate startDate = LocalDate.of(2023, 12, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        int minimumAmount = 10000;
        DayType dayType = DayType.WEEKEND;
        List<EventCondition> eventConditions = List.of(
                createAmountAboveCondition(minimumAmount),
                createDateRangeCondition(startDate, endDate),
                createDayTypeCondition(dayType));
        return new WeekendEvent(eventConditions);
    }

    public static Event createSpecialDayEvent() {
        List<Integer> days = SpecialDay.getDays();
        int minimumAmount = 10000;
        List<EventCondition> eventConditions = List.of(
                createAmountAboveCondition(minimumAmount),
                createSpecificDateCondition(days));
        return new SpecialDayEvent(eventConditions);
    }

    public static EventCondition createDayTypeCondition(DayType dayType) {
        return new DayTypeCondition(dayType);
    }

    public static EventCondition createDateRangeCondition(LocalDate startDate, LocalDate endDate) {
        return new DateRangeCondition(startDate, endDate);
    }

    private static EventCondition createSpecificDateCondition(List<Integer> days) {
        return new SpecificDateCondition(days);
    }

    private static EventCondition createAmountAboveCondition(int amount) {
        return new AmountAboveCondition(amount);
    }

    public static OrderController createOrderController() {
        return new OrderController(createInputView(), createOutputView(), createOrderService());
    }

    private static InputView createInputView() {
        return new InputView(createInput(), createOutput());
    }

    public static OutputView createOutputView() {
        return new OutputView(createOutput());
    }

    private static Output createOutput() {
        return new SystemOutput();
    }

    private static Input createInput() {
        return new ConsoleInput();
    }

    private static OrderService createOrderService() {
        return new OrderService();
    }

}
