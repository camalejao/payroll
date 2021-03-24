package payroll.model.employee;

import java.util.UUID;

import payroll.model.payments.Wage;
import payroll.model.union.UnionMember;

public class Employee {

    private UUID id;

    private String name;

    private String address;

    private Wage wage;

    private UnionMember unionMember;

    
    public Employee() {

    }

    public Employee(UUID id, String name, String address,
                    Wage wage, UnionMember unionMember) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.wage = wage;
        this.unionMember = unionMember;
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


    @Override
    public String toString() {
        String str = "Employee ID: " + getId();
        str += "\nName: " + getName();
        str += "\nAddress: " + getAddress();
        str += getWage().toString();
        if (getUnionMember() != null) {
            str += getUnionMember().toString();
        } else {
            str += "\nUnion: not a union member";
        }
        return str;
    }

}
