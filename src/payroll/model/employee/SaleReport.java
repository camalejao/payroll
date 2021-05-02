package payroll.model.employee;

import java.io.Serializable;
import java.time.LocalDate;

public class SaleReport implements Serializable {

    private LocalDate date;

    private Double value;


    public SaleReport(LocalDate date, Double value) {
        this.date = date;
        this.value = value;
    }


    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getValue() {
        return this.value;
    }

    public void setValue(Double value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "value: " + this.value + ", date: " + this.date.toString();
    }
}
