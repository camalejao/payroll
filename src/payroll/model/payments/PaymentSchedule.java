package payroll.model.payments;

import java.util.ArrayList;
import java.util.List;

public class PaymentSchedule {
    
    private List<String> options;

    public PaymentSchedule() {
        this.options = new ArrayList<String>();
        this.options.add("weekly");
        this.options.add("monthly");
        this.options.add("biweekly");
    }


    public List<String> getOptions() {
        return this.options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    
    public void addOption(String option) {
        this.options.add(option);
    }

}
