package payroll.model.employee;

import java.util.UUID;

import payroll.model.payments.Wage;
import payroll.model.union.UnionMember;

public class Salaried extends Employee {
    
    private Double salary;


    public Salaried() {

    }

    public Salaried(Double salary) {
        this.salary = salary;
    }

    public Salaried(UUID id, String name, String address, Wage wage,
                    UnionMember unionMember, Double salary) {
        super(id, name, address, wage, unionMember);
        this.salary = salary;
    }


    public Double getSalary() {
        return this.salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
