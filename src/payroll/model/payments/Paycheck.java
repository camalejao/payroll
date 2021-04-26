package payroll.model.payments;

import java.time.LocalDate;

import payroll.model.employee.Employee;

public class Paycheck {
    
    private Employee employee;

    private LocalDate date;

    private Double grossPay;

    private Double deductions;

    private boolean includesUnionTax;


    public Paycheck(Employee employee, LocalDate date) {
        this.employee = employee;
        this.date = date;
    }

    public Paycheck(Employee employee, LocalDate date, Double grossPay,
                    Double deductions, boolean includesUnionTax) {
        this.employee = employee;
        this.date = date;
        this.grossPay = grossPay;
        this.deductions = deductions;
        this.includesUnionTax = includesUnionTax;
    }
    

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public Double getGrossPay() {
        return this.grossPay;
    }

    public void setGrossPay(Double grossPay) {
        this.grossPay = grossPay;
    }


    public Double getDeductions() {
        return this.deductions;
    }

    public void setDeductions(Double deductions) {
        this.deductions = deductions;
    }


    public boolean isIncludesUnionTax() {
        return this.includesUnionTax;
    }

    public boolean getIncludesUnionTax() {
        return this.includesUnionTax;
    }

    public void setIncludesUnionTax(boolean includesUnionTax) {
        this.includesUnionTax = includesUnionTax;
    }


    public Double getNetPay() {
        return this.grossPay - this.deductions;
    }

    
    @Override
    public String toString() {
        String str = "\nEmployee:" + this.getEmployee().printBasicInfo();
        str += "\nGross Pay: " + this.getGrossPay();
        str += "\nDeductions: " + this.getDeductions();
        str += "\nNet Pay (gross - deductions): " + this.getNetPay();
        str += "\n==================================\n";
        
        return str;
    }
}
