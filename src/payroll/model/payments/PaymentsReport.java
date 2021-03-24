package payroll.model.payments;

import java.time.LocalDate;
import java.util.List;

public class PaymentsReport {
    
    private List<Paycheck> paychecks;

    private LocalDate date;


    public PaymentsReport(List<Paycheck> paychecks, LocalDate date) {
        this.paychecks = paychecks;
        this.date = date;
    }


    public List<Paycheck> getPaychecks() {
        return this.paychecks;
    }

    public void setPaychecks(List<Paycheck> paychecks) {
        this.paychecks = paychecks;
    }


    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
