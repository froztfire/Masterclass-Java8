package java8.groupExercise2.util;

import java.time.format.DateTimeFormatter;

public enum DateTimeFormatterEnum {

    MMMsDDDscYYYY("MMM dd, yyyy"),
    YYYYhMMhDD("yyyy-MM-dd");

    private DateTimeFormatter dateTimeFormatter;

    DateTimeFormatterEnum(String format) {
        dateTimeFormatter = DateTimeFormatter.ofPattern(format);
    }
    public DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }
}
