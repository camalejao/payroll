package payroll.model.payments;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class PaymentsReport implements Serializable {
    
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


    @Override
    public String toString() {
        String str = "Payments Report\nDate: " + this.date + "\n";
        str += getPaycheckStrings();
        return str;
    }


    private String getPaycheckStrings() {
        String str = "";
        for (Paycheck pc : this.paychecks) {
            str += pc.toString();
        }
        return str;
    }

}
