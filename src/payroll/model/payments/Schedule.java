package payroll.model.payments;

public enum Schedule {
    MONTHLY("monthly"),
    WEEKLY("weekly 1"),
    BIWEEKLY("weekly 2");

    private String scheduleDescription;

    Schedule(String scheduleDescription) {
        this.scheduleDescription = scheduleDescription;
    }

    public String getScheduleDescription() {
        return this.scheduleDescription;
    }
}
