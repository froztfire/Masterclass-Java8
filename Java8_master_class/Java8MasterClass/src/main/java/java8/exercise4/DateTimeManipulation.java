package java8.exercise4;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

//This program is to convert the Christmas Day from PH time zone equivalent to US time zone
public class DateTimeManipulation {
    public static void main(String[] args) {
        ZoneId phTimeZone = ZoneId.of("Asia/Shanghai");
        ZoneId usTimeZone = ZoneId.of("America/Chicago");

        ZonedDateTime phDateTime = ZonedDateTime.now(phTimeZone);
        LocalDateTime currentDateTime = phDateTime.toLocalDateTime();
        LocalDateTime christmasDateTimePH = LocalDateTime.of(currentDateTime.getYear(),
                Month.DECEMBER, 25, 0, 0);
        ZonedDateTime christmasDateTimeEST = christmasDateTimePH.atZone(phTimeZone).withZoneSameInstant(usTimeZone);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy - hh:mm a");
        String phXmasTime = christmasDateTimePH.format(formatter);
        String usXmasTime = christmasDateTimeEST.format(formatter);

        System.out.println("PH: " + phXmasTime + "  ("+phTimeZone+")");
        System.out.println("US: " + usXmasTime + "  ("+usTimeZone+")");

        if (currentDateTime.isAfter(christmasDateTimePH)) {
            christmasDateTimePH = christmasDateTimePH.plusYears(1);
        }
        long months = ChronoUnit.MONTHS.between(currentDateTime, christmasDateTimePH);
        long days = ChronoUnit.DAYS.between(currentDateTime.plusMonths(months), christmasDateTimePH.plusDays(1));
        long remainingDays = currentDateTime.plusDays(-1).until(christmasDateTimePH, ChronoUnit.DAYS);
        System.out.println("Remaining " + months + " month/s and " + days + " days before Christmas!"  );
        System.out.println("Days before Christmas: " + remainingDays);
    }
}
