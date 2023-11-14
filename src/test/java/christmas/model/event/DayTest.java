package christmas.model.event;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DayTest {


    @ParameterizedTest(name = "{0}는 평일")
    @DisplayName("일요일~목요일까지는 평일 TRUE")
    @ValueSource(strings = {"SUNDAY", "TUESDAY", "THURSDAY"})
    void 일요일에서_목요일은_평일_TRUE(String dayOfTheWeek) {
        // when
        boolean weekday = Day.isWeekday(dayOfTheWeek);

        // then
        Assertions.assertThat(weekday).isTrue();
    }

    @ParameterizedTest(name = "{0}는 평일 FALSE")
    @DisplayName("금요일에서 토요일은 평일 FALSE")
    @ValueSource(strings = {"FRIDAY", "SATURDAY"})
    void 금요일에서_토요일은_평일_FALSE(String dayOfTheWeek) {
        // when
        boolean weekday = Day.isWeekday(dayOfTheWeek);

        // then
        Assertions.assertThat(weekday).isFalse();
    }

    @ParameterizedTest(name = "{0}는 주말 TRUE")
    @DisplayName("금요일에서 토요일은 주말이다.")
    @ValueSource(strings = {"FRIDAY", "SATURDAY"})
    void 금요일에서_토요일은_주말(String dayOfTheWeek) {
        // when
        boolean weekday = Day.isWeekend(dayOfTheWeek);

        // then
        Assertions.assertThat(weekday).isTrue();
    }

    @ParameterizedTest(name = "{0}는 주말 FALSE")
    @DisplayName("일요일~목요일까지는 주말 FALSE")
    @ValueSource(strings = {"SUNDAY", "TUESDAY", "THURSDAY"})
    void 일요일에서_목요일은_주말_FALSE(String dayOfTheWeek) {
        // when
        boolean weekday = Day.isWeekend(dayOfTheWeek);

        // then
        Assertions.assertThat(weekday).isFalse();
    }


}