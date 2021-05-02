package payroll.model.union;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UnionMember implements Serializable {
    
    private UUID id;
    
    private boolean active;

    private Double fee;

    private List<ServiceTax> serviceTaxes;


    public UnionMember(UUID id, boolean active, Double fee) {
        this.id = id;
        this.active = active;
        this.fee = fee;
        this.serviceTaxes = new ArrayList<ServiceTax>();
    }


    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public boolean isActive() {
        return this.active;
    }

    public boolean getActive() {
        return this.active;
    }


    public void setActive(boolean active) {
        this.active = active;
    }

    public Double getFee() {
        return this.fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }
    

    public List<ServiceTax> getServiceTaxes() {
        return this.serviceTaxes;
    }

    public void setServiceTaxes(List<ServiceTax> serviceTaxes) {
        this.serviceTaxes = serviceTaxes;
    }


    @Override
    public String toString() {
        String str = "\nUnion ID: " + getId();
        str += "\nIs an Active Union Member: " + getActive();
        str += "\nUnion Fee: " + getFee();
        str += "\nServiceTaxes='" + getServiceTaxStrings() + "'";
        return str;
    }


    private String getServiceTaxStrings() {
        String str = "";
        for (ServiceTax st : this.serviceTaxes) {
            str += "(" + st.toString() + "), ";
        }
        return str;
    }
}
