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
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class AppConfig {

    public static EventPlanner createEventPlanner() {
        List<Event> events = List.of(createChristmasEvent(), createGiftEvent(),
                createWeekendEvent(),
                createWeekdayEvent(), createSpecialDayEvent());
        return new EventPlanner(events);
    }

    private static Event createChristmasEvent() {
        return new ChristmasEvent();
    }

    private static Event createGiftEvent() {
        return new GiftEvent();
    }

    private static Event createWeekdayEvent() {
        return new WeekdayEvent();
    }

    private static Event createWeekendEvent() {
        return new WeekendEvent();
    }

    private static Event createSpecialDayEvent() {
        return new SpecialDayEvent();
    }

    public static OrderController createOrderController() {
        return new OrderController(createInputView(), createOutputView());
    }

    private static InputView createInputView() {
        return new InputView(createInput(), createOutput());
    }

    private static OutputView createOutputView() {
        return new OutputView(createOutput());
    }

    private static Output createOutput() {
        return new SystemOutput();
    }

    private static Input createInput() {
        return new ConsoleInput();
    }

}
