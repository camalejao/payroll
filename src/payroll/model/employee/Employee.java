package payroll.model.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import payroll.model.payments.Wage;
import payroll.model.union.UnionMember;

public class Employee {

    private UUID id;

    private String name;

    private String address;

    private Wage wage;

    private UnionMember unionMember;

    private List<SaleReport> saleReports;

    private List<Timecard> timecards;


    public Employee(UUID id, String name, String address,
                    Wage wage, UnionMember unionMember) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.wage = wage;
        this.unionMember = unionMember;
        this.saleReports = new ArrayList<SaleReport>();
        this.timecards = new ArrayList<Timecard>();
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


    public Wage getWage() {
        return this.wage;
    }

    public void setWage(Wage wage) {
        this.wage = wage;
    }

    
    public UnionMember getUnionMember() {
        return this.unionMember;
    }

    public void setUnionMember(UnionMember unionMember) {
        this.unionMember = unionMember;
    }


    public List<SaleReport> getSaleReports() {
        return this.saleReports;
    }

    public void setSaleReports(List<SaleReport> saleReports) {
        this.saleReports = saleReports;
    }


    public List<Timecard> getTimecards() {
        return this.timecards;
    }

    public void setTimecards(List<Timecard> timecards) {
        this.timecards = timecards;
    }


    @Override
    public String toString() {
        String str = "Employee ID: " + getId();
        str += "\nName: " + getName();
        str += "\nAddress: " + getAddress();
        str += getWage().toString();
        if (getUnionMember() != null) {
            str += getUnionMember().toString();
        } else {
            str += "Union: not a union member";
        }
        return str;
    }

}
