package payroll.model.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import payroll.model.payments.PaymentInfo;
import payroll.model.union.UnionMember;

public class Hourly extends Employee {
    
    private Double hourlyRate;

    private List<Timecard> timecards;


    public Hourly() {

    }

    public Hourly(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
        this.timecards = new ArrayList<Timecard>();
    }

    public Hourly(UUID id, String name, String address, UnionMember unionMember,
                    PaymentInfo paymentInfo, Double hourlyRate) {
        super(id, name, address, unionMember, paymentInfo);
        this.hourlyRate = hourlyRate;
        this.timecards = new ArrayList<Timecard>();
    }


    public Double getHourlyRate() {
        return this.hourlyRate;
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }


    public List<Timecard> getTimecards() {
        return this.timecards;
    }

    public void setTimecards(List<Timecard> timecards) {
        this.timecards = timecards;
    }


    @Override
    public String toString() {
        return super.toString() + "\nHourly: {" +
            " hourlyRate='" + getHourlyRate() + "'" +
            ", timecards='" + getTimecards() + "'" +
            "}";
    }

    @Override
    Double calcPayment() {
        // TODO Auto-generated method stub
        return null;
    }
}
