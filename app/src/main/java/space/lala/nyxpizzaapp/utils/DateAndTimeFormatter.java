package space.lala.nyxpizzaapp.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateAndTimeFormatter {
    private DateAndTimeFormatter() {
    }

    public static String formatDate(LocalDate date) {
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd.MM.yy");
        return formatterDate.format(date);
    }

    public static String formatTime(LocalTime time) {
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
        return formatterTime.format(time);
    }
}
