package payroll.model.payments;

public enum PaymentMethod {
    MAIL_CHECK("Check sent by mail"),
    HANDS_CHECK("Check on hands"),
    BANK_DEPOSIT("Bank Deposit");

    private String methodDescription;

    PaymentMethod(String methodDescription) {
        this.methodDescription = methodDescription;
    }

    public String getMethodDescription() {
        return this.methodDescription;
    }
}
