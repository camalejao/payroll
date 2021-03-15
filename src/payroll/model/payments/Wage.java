package payroll.model.payments;

public class Wage {
    
    private WageType wageType;

    private PaymentMethod paymentMethod;

    private Double value;

    private Double commissionRate;


    public Wage(WageType wageType, PaymentMethod paymentMethod,
                Double value, Double commissionRate) {
        this.wageType = wageType;
        this.paymentMethod = paymentMethod;
        this.value = value;
        this.commissionRate = commissionRate;
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

    public Double getValue() {
        return this.value;
    }

    public void setValue(Double value) {
        this.value = value;
    }


    public Double getCommissionRate() {
        return this.commissionRate;
    }

    public void setCommissionRate(Double commissionRate) {
        this.commissionRate = commissionRate;
    }


    @Override
    public String toString() {
        String str = "\nWage type: " + getWageType().getWageDescription();
        str += "\nWage value: " + getValue();
        str += "\nCommission Rate: " + getCommissionRate();
        return str;
    }

}
