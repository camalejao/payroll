package payroll.model.payments;

public enum WageType {
    HOURLY("Hourly"),
    SALARIED("Salaried"),
    COMMISSIONED("Commissioned");

    private String wageDescription;

    WageType(String wageDescription) {
        this.wageDescription = wageDescription;
    }

    public String getWageDescription() {
        return this.wageDescription;
    }
}
