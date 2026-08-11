package com.example.tutorial.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private DateUtil() {
        // Prevent creating an object from this utility class
    }

    public static String dateYmd(LocalDate date) {
        if (date == null) {
            return null;
        }

        return date.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
        );
    }

    public static String dateDMY(LocalDate date) {
        if (date == null) {
            return null;
        }

        return date.format(
                DateTimeFormatter.ofPattern("dd-MMM-yyyy")
        );
    }

    public static String dateTimeFormat(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }

        return dateTime.format(
                DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a")
        );
    }
}