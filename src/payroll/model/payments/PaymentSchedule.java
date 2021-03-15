package payroll.model.payments;

import java.util.ArrayList;
import java.util.List;

public class PaymentSchedule {
    
    private List<String> options;

    public PaymentSchedule() {
        this.options = new ArrayList<String>();
        this.options.add("semanalmente");
        this.options.add("mensalmente");
        this.options.add("bi-semanalmente");
    }
}
