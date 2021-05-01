package payroll.app;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import payroll.app.util.ConsoleUtils;
import payroll.app.util.EmployeeUtils;
import payroll.model.employee.Commissioned;
import payroll.model.employee.Employee;
import payroll.model.employee.Hourly;
import payroll.model.employee.Salaried;
import payroll.model.employee.SaleReport;
import payroll.model.employee.Timecard;
import payroll.model.payments.PaymentInfo;
import payroll.model.payments.PaymentMethod;
import payroll.model.payments.PaymentSchedule;
import payroll.model.union.ServiceTax;
import payroll.model.union.UnionMember;

public class EmployeeMenu {

    public static Employee registerEmployee(Scanner input, List<PaymentSchedule> paymentSchedules) {

        Employee employee;
        PaymentInfo paymentInfo = null;
        PaymentSchedule paymentSchedule = null;
        UnionMember unionMember = null;

        int answer;

        String name = ConsoleUtils.readStringInput(input, "\nEnter the employee's name:");
        String address = ConsoleUtils.readStringInput(input, "Enter the address:");

        System.out.println("What is the type of employee? (enter 1, 2 or 3)");
        System.out.printf("[1] Hourly\n[2] Salaried\n[3] Commissioned\n");
        answer = input.nextInt();
        System.out.println();

        if (answer == 1) {
            Double hourlyRate = ConsoleUtils.readDoubleInput(input, "Enter the hourly rate/wage:");
            employee = new Hourly(UUID.randomUUID(), name, address, unionMember, paymentInfo, hourlyRate);
            paymentSchedule = paymentSchedules.get(0);

        } else if (answer == 2) {
            Double salary = ConsoleUtils.readDoubleInput(input, "Enter the salary:");
            employee = new Salaried(UUID.randomUUID(), name, address, unionMember, paymentInfo, salary);
            paymentSchedule = paymentSchedules.get(1);

        } else if (answer == 3) {
            Double salary = ConsoleUtils.readDoubleInput(input, "Enter the salary:");
            Double commissionRate = ConsoleUtils.readDoubleInput(input, "Enter the Commission Rate (NOTE: this value will be divided by 100):");
            employee = new Commissioned(UUID.randomUUID(), name, address, unionMember, paymentInfo, salary, commissionRate);
            paymentSchedule = paymentSchedules.get(2);

        } else {
            System.out.println("Invalid Option. Proceeding with salaried");
            Double salary = ConsoleUtils.readDoubleInput(input, "Enter the salary:");
            employee = new Salaried(UUID.randomUUID(), name, address, unionMember, paymentInfo, salary);
            paymentSchedule = paymentSchedules.get(1);
        }

        paymentInfo = PaymentsMenu.getPaymentInfoInput(input);
        paymentInfo.setPaymentSchedule(paymentSchedule);
        employee.setPaymentInfo(paymentInfo);

        if (ConsoleUtils.confirmation(input, "Is the employee a union member?")) {
            Double unionFee = ConsoleUtils.readDoubleInput(input, "Enter the monthly union fee:");
            unionMember = new UnionMember(UUID.randomUUID(), true, unionFee);
            employee.setUnionMember(unionMember);
        }

        System.out.println("\nNew employee registered!");
        System.out.println(employee.toString());

        return employee;
    }

    public static void printEmployees(List<Employee> employeeList) {
        int i = 1;
        System.out.println("\n\nEmployee List:");
        for (Employee e : employeeList) {
            System.out.println("\nEmployee #" + i);
            System.out.println(e.toString());
            System.out.println("\n=======================================================");
            i++;
        }
    }

    public static int removeEmployee(Scanner input, List<Employee> employeeList) {
        return EmployeeUtils.getEmployeeIndex(input, employeeList);
    }

    public static void addTimecard(Scanner input, List<Employee> hourlyEmployees) {
        if (!hourlyEmployees.isEmpty()) {
            Hourly emp = (Hourly) hourlyEmployees.get(
                EmployeeUtils.getEmployeeIndex(input, hourlyEmployees));
            
            LocalDate date = ConsoleUtils.readDateInput(input);

            int hourIn = ConsoleUtils.readIntInput(input, "Enter hour IN:");
            int minuteIn = ConsoleUtils.readIntInput(input, "Enter minutes IN:");
            LocalTime timeIn = LocalTime.of(hourIn, minuteIn);

            int hourOut = ConsoleUtils.readIntInput(input, "Enter hour OUT:");
            int minuteOut = ConsoleUtils.readIntInput(input, "Enter minutes OUT:");
            LocalTime timeOut = LocalTime.of(hourOut, minuteOut);

            Timecard timecard = new Timecard(date, timeIn, timeOut);
            emp.getTimecards().add(timecard);
            System.out.println("Timecard added");
        } else {
            System.out.println("There are no hourly employees to add timecard");
        }
    }

    public static void addSaleReport(Scanner input, List<Employee> commissionedEmployees) {
        if (!commissionedEmployees.isEmpty()) {
            Commissioned emp = (Commissioned) commissionedEmployees.get(
                EmployeeUtils.getEmployeeIndex(input, commissionedEmployees));
            LocalDate date = ConsoleUtils.readDateInput(input);
            Double value = ConsoleUtils.readDoubleInput(input, "Enter sale value:");
            
            SaleReport saleReport = new SaleReport(date, value);
            emp.getSaleReports().add(saleReport);
            System.out.println("Sale Report added");
        } else {
            System.out.println("There are no commisioned employees to add sale report");
        }
    }

    public static void addServiceTax(Scanner input, List<Employee> unionEmployees) {
        if (!unionEmployees.isEmpty()) {
            Employee emp = unionEmployees.get(
                EmployeeUtils.getEmployeeIndex(input, unionEmployees));
            LocalDate date = ConsoleUtils.readDateInput(input);
            Double value = ConsoleUtils.readDoubleInput(input, "Enter tax value");

            ServiceTax serviceTax = new ServiceTax(date, value);
            emp.getUnionMember().getServiceTaxes().add(serviceTax);
            System.out.println("Service Tax added");
        } else {
            System.out.println("There are no Union members to add service tax");
        }
    }

    public static void editEmployee(Scanner input, List<Employee> employeeList, 
                                    List<PaymentSchedule> paymentSchedules) {
        int idx = EmployeeUtils.getEmployeeIndex(input, employeeList);
        Employee emp = employeeList.get(idx);

        System.out.println("Select Attribute to edit");
        System.out.println("[1] Name");
        System.out.println("[2] Address");
        System.out.println("[3] Type");
        System.out.println("[4] Payment Method");
        System.out.println("[5] Union Member Status");
        System.out.println("[6] Union Monthly Tax");
        System.out.println("[7] Payment Schedule");
        System.out.println("[0] Cancel");

        int option = ConsoleUtils.readIntInput(input, "");
        switch (option) {
            case 1:
                String name = ConsoleUtils.readStringInput(input, "Enter updated name:");
                emp.setName(name);
                break;
            case 2:
                String address = ConsoleUtils.readStringInput(input, "Enter updated address:");
                emp.setAddress(address);
                break;
            case 3:
                System.out.println("What is the type of employee? (enter 1, 2 or 3)");
                System.out.printf("[1] Hourly\n[2] Salaried\n[3] Commissioned\n");
                int type = ConsoleUtils.readIntInput(input, "");
                if (type == 1 && !(emp instanceof Hourly)) {
                    Double hourlyRate = ConsoleUtils.readDoubleInput(input, "Enter the hourly rate/wage:");
                    emp = new Hourly(emp.getId(), emp.getName(), emp.getAddress(),
                        emp.getUnionMember(), emp.getPaymentInfo(), hourlyRate);
                
                } else if (type == 2 && !(emp instanceof Salaried)) {
                    Double salary = ConsoleUtils.readDoubleInput(input, "Enter the salary:");
                    emp = new Salaried(emp.getId(), emp.getName(), emp.getAddress(),
                        emp.getUnionMember(), emp.getPaymentInfo(), salary);
                
                } else if (type == 3 && !(emp instanceof Commissioned)) {
                    Double salary = ConsoleUtils.readDoubleInput(input, "Enter the salary:");
                    String msg = "Enter the commission rate (this value will be divided by 100 to get a %):";
                    Double commissionRate = ConsoleUtils.readDoubleInput(input, msg);
                    emp = new Commissioned(emp.getId(), emp.getName(), emp.getAddress(),
                        emp.getUnionMember(), emp.getPaymentInfo(), salary, commissionRate);

                } else {
                    System.out.println("Invalid Option or Employee already is the selected type.");
                }
                break;
            case 4:
                int i = 1;
                String msg = "Select payment method (enter 1, 2 or 3):\n";
                for (PaymentMethod pm : PaymentMethod.values()) {
                    msg += "[" + i + "]. " + pm.getMethodDescription() + "\n";
                    i++;
                }
                int answer = ConsoleUtils.readIntInput(input, msg);
                if (answer >= 1 && answer <= 3) {
                    emp.getPaymentInfo().setPaymentMethod(PaymentMethod.values()[answer - 1]);
                    System.out.println("Done!");
                    System.out.println(emp.toString());
                } else {
                    System.out.println("Invalid option");
                }
                break;
            case 5:
                if (emp.getUnionMember() == null) {
                    System.out.println("Employee is not a union member\n");
                    if (ConsoleUtils.confirmation(input, "Proceed to registration?")) {
                        Double unionFee = ConsoleUtils.readDoubleInput(input, "Enter the monthly union fee:");
                        emp.setUnionMember(new UnionMember(UUID.randomUUID(), true, unionFee));
                    }
                } else if (emp.getUnionMember().isActive()) {
                    System.out.println("Employee is an active union member");
                    if (ConsoleUtils.confirmation(input, "Inactivate membership?")) {
                        emp.getUnionMember().setActive(false);
                    }
                } else {
                    System.out.println("Employee is an inactive union member");
                    if (ConsoleUtils.confirmation(input, "Reactivate membership?")) {
                        emp.getUnionMember().setActive(true);
                    }
                }
                break;
            case 6:
                if (emp.getUnionMember() == null) {
                    System.out.println("Employee is not a union member");
                } else {
                    Double unionFee = ConsoleUtils.readDoubleInput(input, "Enter the monthly union fee:");
                    emp.getUnionMember().setFee(unionFee);
                }
                break;
            case 7:
                int count = 1;
                String str = "Select payment schedule:\n";
                for (PaymentSchedule ps : paymentSchedules) {
                    str += count + ") " + ps.toString() + "\n";
                    count++;
                }
                int psIdx = ConsoleUtils.readIntInput(input, str);
                if (psIdx >= 1 && psIdx <= count) {
                    emp.getPaymentInfo().setPaymentSchedule(paymentSchedules.get(psIdx - 1));
                } else {
                    System.out.println("Invalid option, current schedule will be kept.");
                }
                break;
            default:
                break;
        }
    }
}
