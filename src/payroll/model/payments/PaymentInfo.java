package payroll.model.payments;

public class PaymentInfo {
    
    private int bank;

    private int agency;

    private int account;

    private String schedule;

    private PaymentMethod paymentMethod;


    public PaymentInfo() {

    }


    public PaymentInfo(int bank, int agency, int account,
                        String schedule, PaymentMethod paymentMethod) {
        this.bank = bank;
        this.agency = agency;
        this.account = account;
        this.schedule = schedule;
        this.paymentMethod = paymentMethod;
    }


    public int getBank() {
        return this.bank;
    }

    public void setBank(int bank) {
        this.bank = bank;
    }


    public int getAgency() {
        return this.agency;
    }

    public void setAgency(int agency) {
        this.agency = agency;
    }


    public int getAccount() {
        return this.account;
    }

    public void setAccount(int account) {
        this.account = account;
    }


    public String getSchedule() {
        return this.schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }


    public PaymentMethod getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }


    @Override
    public String toString() {
        return "{" +
            " bank='" + getBank() + "'" +
            ", agency='" + getAgency() + "'" +
            ", account='" + getAccount() + "'" +
            ", schedule='" + getSchedule() + "'" +
            ", paymentMethod='" + getPaymentMethod().getMethodDescription() + "'" +
            "}";
    }
}