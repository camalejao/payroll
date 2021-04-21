package payroll.model.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import payroll.model.payments.PaymentInfo;
import payroll.model.union.UnionMember;

public class Commissioned extends Employee {

    private Double fixedSalary;
    
    private Double commissionRate;

    private List<SaleReport> saleReports;
    

    public Commissioned() {

    }

    public Commissioned(Double fixedSalary, Double commissionRate) {
        this.fixedSalary = fixedSalary;
        this.commissionRate = commissionRate;
        this.saleReports = new ArrayList<SaleReport>();
    }

    public Commissioned(UUID id, String name, String address, UnionMember unionMember,
                        PaymentInfo paymentInfo, Double fixedSalary, Double commissionRate) {
        super(id, name, address, unionMember, paymentInfo);
        this.fixedSalary = fixedSalary;
        this.commissionRate = commissionRate;
        this.saleReports = new ArrayList<SaleReport>();
    }


    public Double getFixedSalary() {
        return this.fixedSalary;
    }

    public void setFixedSalary(Double fixedSalary) {
        this.fixedSalary = fixedSalary;
    }


    public Double getCommissionRate() {
        return this.commissionRate;
    }

    public void setCommissionRate(Double commissionRate) {
        this.commissionRate = commissionRate;
    }


    public List<SaleReport> getSaleReports() {
        return this.saleReports;
    }

    public void setSaleReports(List<SaleReport> saleReports) {
        this.saleReports = saleReports;
    }



    @Override
    public String toString() {
        return super.toString() + "\nCommissioned: {" +
            " fixedSalary='" + getFixedSalary() + "'" +
            ", commissionRate='" + getCommissionRate() + "'" +
            ", saleReports='" + getSaleReports() + "'" +
            "}";
    }

    @Override
    Double calcPayment() {
        // TODO Auto-generated method stub
        return null;
    }
}
