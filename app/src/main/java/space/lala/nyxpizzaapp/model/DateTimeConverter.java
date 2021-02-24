package space.lala.nyxpizzaapp.model;

import androidx.room.TypeConverter;

import java.time.LocalDateTime;


public class DateTimeConverter {

    @TypeConverter
    public static LocalDateTime toDate(String dateTimeString) {
        if (dateTimeString == null) {
            return null;
        } else {
            return LocalDateTime.parse(dateTimeString);
        }
    }

    @TypeConverter
    public static String toDateString(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        } else {
            return dateTime.toString();
        }
    }
}
