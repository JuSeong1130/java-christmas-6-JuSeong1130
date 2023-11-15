package christmas.util;

import christmas.config.AppConfig;
import christmas.exception.InvalidFormatException;
import christmas.view.OutputView;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class ExceptionRetryHandler {

    private static final OutputView outputView = AppConfig.createOutputView();

    public static <T> T retryUntilSuccess(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage() + System.lineSeparator());
            }
        }
    }

    public static <T, U, R> R retryUntilSuccess(BiFunction<T, List<U>, R> function, T arguments,
            List<U> otherArguments) {
        while (true) {
            try {
                return function.apply(arguments, otherArguments);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage() + System.lineSeparator());
            }
        }
    }
}
