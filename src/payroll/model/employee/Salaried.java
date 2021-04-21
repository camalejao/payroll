package payroll.model.employee;

import java.util.UUID;

import payroll.model.payments.PaymentInfo;
import payroll.model.union.UnionMember;

public class Salaried extends Employee {
    
    private Double salary;


    public Salaried() {

    }

    public Salaried(Double salary) {
        this.salary = salary;
    }

    public Salaried(UUID id, String name, String address, UnionMember unionMember,
                    PaymentInfo paymentInfo, Double salary) {
        super(id, name, address, unionMember, paymentInfo);
        this.salary = salary;
    }


    public Double getSalary() {
        return this.salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }


    @Override
    public String toString() {
        return super.toString() + "\nSalaried: {" +
            " salary='" + getSalary() + "'" +
            "}";
    }

    @Override
    Double calcPayment() {
        return this.salary;
    }

}
