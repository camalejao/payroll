package payroll.model.payments;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

import payroll.app.util.DateUtils;

public class PaymentSchedule implements Serializable {
    
    private Schedule schedule;

    private Integer dayOfMonth;

    private DayOfWeek dayOfWeek;

    
    public PaymentSchedule(Schedule schedule, Integer dayOfMonth, DayOfWeek dayOfWeek) {
        this.schedule = schedule;
        this.dayOfMonth = dayOfMonth;
        this.dayOfWeek = dayOfWeek;
    }


    public Schedule getSchedule() {
        return this.schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }


    public Integer getDayOfMonth() {
        return this.dayOfMonth;
    }

    public void setDayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }


    public DayOfWeek getDayOfWeek() {
        return this.dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }


    @Override
    public String toString() {
        String str = this.schedule.getScheduleDescription() + " ";

        if (this.schedule == Schedule.MONTHLY) {
            if (this.dayOfMonth != null) {
                str += this.dayOfMonth;
            } else {
                str += "$";
            }
        } else {
            str += this.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.US);
        }

        return str;
    }


    public int getDividingFactor() {
        if (this.getSchedule() == Schedule.MONTHLY) {
            return 1;
        } else if (this.getSchedule() == Schedule.WEEKLY) {
            return 4;
        } else { // BIWEEKLY
            return 2;
        }
    }


    public boolean checkIfDateIsInSchedule(int weekCounter, LocalDate date) {
        if (this.schedule == Schedule.MONTHLY) {

            if (this.dayOfMonth != null) {
                return this.dayOfMonth == date.getDayOfMonth();
            } else {
                return date.isEqual(DateUtils.getLastWorkingDateOfMonth(date.with(TemporalAdjusters.lastDayOfMonth())));
            }

        } else if (this.schedule == Schedule.WEEKLY) {
            
            return this.dayOfWeek == date.getDayOfWeek();

        } else if (this.schedule == Schedule.BIWEEKLY) {

            return this.dayOfWeek == date.getDayOfWeek() && weekCounter % 2 == 0;

        }

        return false;
    }
}
