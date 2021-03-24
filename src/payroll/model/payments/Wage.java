package payroll.model.payments;

public class Wage {
    
    private WageType wageType;

    private PaymentMethod paymentMethod;

    private String schedule;


    public Wage(WageType wageType, PaymentMethod paymentMethod, String schedule) {
        this.wageType = wageType;
        this.paymentMethod = paymentMethod;
        this.schedule = schedule;
    }


    public WageType getWageType() {
        return this.wageType;
    }

    public void setWageType(WageType wageType) {
        this.wageType = wageType;
    }

    
    public PaymentMethod getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    

    public String getSchedule() {
        return this.schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }


    @Override
    public String toString() {
        String str = "\nWage type: " + getWageType().getWageDescription();
        return str;
    }

}
