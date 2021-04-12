package payroll.model.employee;

import java.util.UUID;

import payroll.model.payments.PaymentInfo;
import payroll.model.union.UnionMember;

public class Employee {

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
}
