package payroll.app.util;

import java.time.LocalDate;

public class DateUtils {
    public static LocalDate getLastWorkingDateOfMonth(LocalDate lastDayOfMonth) {
        LocalDate lastWorkingDateOfMonth;
        switch (lastDayOfMonth.getDayOfWeek()) {
            case SATURDAY:
                lastWorkingDateOfMonth = lastDayOfMonth.minusDays(1);
                break;
            case SUNDAY:
                lastWorkingDateOfMonth = lastDayOfMonth.minusDays(2);
                break;
            default:
                lastWorkingDateOfMonth = lastDayOfMonth;
                break;
        }
        return lastWorkingDateOfMonth;
    }
}
