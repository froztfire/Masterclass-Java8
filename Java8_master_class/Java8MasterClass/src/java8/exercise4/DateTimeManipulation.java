package java8.exercise4;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeManipulation {
    public static void main(String[] args) {
        ZoneId phTimeZone = ZoneId.of("Asia/Shanghai");
        ZoneId usTimeZone = ZoneId.of("America/Chicago");

        ZonedDateTime phDateTime = ZonedDateTime.now(phTimeZone);
        ZonedDateTime usDateTime = phDateTime.withZoneSameInstant(usTimeZone);

        LocalDateTime currentDateTime = phDateTime.toLocalDateTime();
        LocalDateTime christmasDateTime = LocalDateTime.of(currentDateTime.getYear(), Month.DECEMBER, 25, 0, 0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy - hh:mm a");
        String phXmasTime = christmasDateTime.format(formatter);

        System.out.println("PH: " + phXmasTime + "  ("+phTimeZone+")");
        System.out.println("US: " + usDateTime.plusMonths(6).plusDays(17).format(formatter) + "  ("+usTimeZone+")");

        if (currentDateTime.isAfter(christmasDateTime)) {
            christmasDateTime = christmasDateTime.plusYears(1);
        }
        long months = ChronoUnit.MONTHS.between(currentDateTime, christmasDateTime);
        long days = ChronoUnit.DAYS.between(currentDateTime.plusMonths(months), christmasDateTime.plusDays(1));
        long remainingDays = currentDateTime.plusDays(-1).until(christmasDateTime, ChronoUnit.DAYS);
        System.out.println("Remaining " + months + " month/s and " + days + " days before Christmas!"  );
        System.out.println("Days before Christmas: " + remainingDays);
    }
}
