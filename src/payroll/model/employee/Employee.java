package payroll.model.employee;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import payroll.model.payments.Paycheck;
import payroll.model.payments.PaymentInfo;
import payroll.model.union.ServiceTax;
import payroll.model.union.UnionMember;

public abstract class Employee implements Serializable {

    private UUID id;

    private String name;

    private String address;

    private UnionMember unionMember;

    private PaymentInfo paymentInfo;

    
    public Employee() {

    }

    public Employee(UUID id, String name, String address,
                    UnionMember unionMember, PaymentInfo paymentInfo) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.unionMember = unionMember;
        this.paymentInfo = paymentInfo;
    }
    

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    
    public UnionMember getUnionMember() {
        return this.unionMember;
    }

    public void setUnionMember(UnionMember unionMember) {
        this.unionMember = unionMember;
    }


    public PaymentInfo getPaymentInfo() {
        return this.paymentInfo;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }


    @Override
    public String toString() {
        String str = "Employee ID: " + getId();
        str += "\nName: " + getName();
        str += "\nAddress: " + getAddress();
        str += "\nPayment Info: " + getPaymentInfo().toString();
        if (getUnionMember() != null) {
            str += getUnionMember().toString();
        } else {
            str += "\nUnion: not a union member";
        }
        return str;
    }

    public String printBasicInfo() {
        return this.getName() + " id:" + this.getId();
    }


    public Double getUnionFee() {
        Double fee = 0.0;
        UnionMember unionMember = this.getUnionMember();
        if (unionMember != null) {
            if (unionMember.isActive()) fee += this.getUnionMember().getFee();
        }
        return fee;
    }


    public Double calcServiceTaxes() {
        Double taxes = 0.0;
        List<Paycheck> paychecks = this.getPaymentInfo().getPaychecks();
        List<ServiceTax> validTaxes;
        UnionMember unionMember = this.getUnionMember();
        
        if (unionMember != null) {
            if (paychecks != null && !paychecks.isEmpty()) {
                LocalDate lastPaymentDate = paychecks.get(paychecks.size() - 1).getDate();
                Predicate<ServiceTax> dateFilter = tax -> tax.getDate().isAfter(lastPaymentDate);
                validTaxes = unionMember.getServiceTaxes().stream().filter(dateFilter).collect(Collectors.toList());
            } else {
                validTaxes = unionMember.getServiceTaxes();
            }

            for (ServiceTax s : validTaxes) {
               taxes += s.getValue(); 
            }
        }

        return taxes;
    }

    public Paycheck processPayment(LocalDate paymentDate) {
        Paycheck newPaycheck = null;

        Double grossPay = this.calcPayment(paymentDate);
        Double deductions = this.calcServiceTaxes();
        Double unionFee = this.getUnionFee();
        boolean includes = false;
        
        if (unionFee > 0.0) {
            Paycheck lastPayment = this.getPaymentInfo().getLastPayment();

            if (lastPayment != null) {
                LocalDate lastPaymentDate = lastPayment.getDate();
                
                if (!(lastPaymentDate.getMonthValue() == paymentDate.getMonthValue()
                    && lastPaymentDate.getYear() == paymentDate.getYear())) {
                    deductions += unionFee;
                    includes = true;
                }

            } else {
                deductions += unionFee;
                includes = true;
            }
        }

        newPaycheck = new Paycheck(this, paymentDate, grossPay, deductions, includes);

        this.getPaymentInfo().getPaychecks().add(newPaycheck);

        return newPaycheck;
    }

    abstract Double calcPayment(LocalDate paymentDate);
}
