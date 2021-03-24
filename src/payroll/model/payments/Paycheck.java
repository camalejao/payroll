package payroll.model.payments;

import java.time.LocalDate;

import payroll.model.employee.Employee;

public class Paycheck {
    
    private Employee employee;

    private Double value;

    private LocalDate date;

    private Double hours;

    private Double extraHours;

    private Double commissions;


    public Paycheck(Employee employee, Double value, LocalDate date,
                    Double hours, Double extraHours, Double commissions) {
        this.employee = employee;
        this.value = value;
        this.date = date;
        this.hours = hours;
        this.extraHours = extraHours;
        this.commissions = commissions;
    }
    

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    public Double getValue() {
        return this.value;
    }

    public void setValue(Double value) {
        this.value = value;
    }


    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public Double getHours() {
        return this.hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }


    public Double getExtraHours() {
        return this.extraHours;
    }

    public void setExtraHours(Double extraHours) {
        this.extraHours = extraHours;
    }


    public Double getCommissions() {
        return this.commissions;
    }

    public void setCommissions(Double commissions) {
        this.commissions = commissions;
    }

}
