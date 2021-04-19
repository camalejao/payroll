package payroll.app;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import payroll.app.util.ConsoleUtils;
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

    public static Employee registerEmployee(Scanner input, PaymentSchedule paymentSchedules) {

        Employee employee;
        PaymentInfo paymentInfo = null;
        UnionMember unionMember = null;

        int answer;
        String schedule = "";

        String name = ConsoleUtils.readStringInput(input, "\nEnter the employee's name:");
        String address = ConsoleUtils.readStringInput(input, "Enter the address:");

        System.out.println("What is the type of employee? (enter 1, 2 or 3)");
        System.out.printf("[1] Hourly\n[2] Salaried\n[3] Commissioned\n");
        answer = input.nextInt();
        System.out.println();
        schedule = paymentSchedules.getOptions().get(answer - 1);

        if (answer == 1) {
            Double hourlyRate = ConsoleUtils.readDoubleInput(input, "Enter the hourly rate/wage:");
            employee = new Hourly(UUID.randomUUID(), name, address, unionMember, paymentInfo, hourlyRate);

        } else if (answer == 2) {
            Double salary = ConsoleUtils.readDoubleInput(input, "Enter the salary:");
            employee = new Salaried(UUID.randomUUID(), name, address, unionMember, paymentInfo, salary);

        } else if (answer == 3) {
            Double salary = ConsoleUtils.readDoubleInput(input, "Enter the salary:");
            Double commissionRate = ConsoleUtils.readDoubleInput(input, "Enter the Commission Rate:");
            employee = new Commissioned(UUID.randomUUID(), name, address, unionMember, paymentInfo, salary, commissionRate);
        
        } else {
            employee = new Employee(UUID.randomUUID(), name, address, unionMember, paymentInfo);
        }

        paymentInfo = PaymentsMenu.getPaymentInfoInput(input, schedule);
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

    public static void removeEmployee(Scanner input, List<Employee> employeeList) {
        employeeList.remove(getEmployeeIndex(input, employeeList));
        System.out.println("Employee Removed");
    }

    public static void addTimecard(Scanner input, List<Employee> employeeList) {
        Predicate<Employee> hourlyFilter = employee -> employee instanceof Hourly;
        List<Employee> hourlyEmployees = employeeList.stream().filter(hourlyFilter).collect(Collectors.toList());
        
        if (!hourlyEmployees.isEmpty()) {
            int index = getEmployeeIndex(input, hourlyEmployees);
            
            Hourly emp = (Hourly) hourlyEmployees.get(index);
            
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

    public static void addSaleReport(Scanner input, List<Employee> employeeList) {
        Predicate<Employee> commissionedFilter = employee -> employee instanceof Commissioned;
        List<Employee> commissionedEmployees = employeeList.stream().filter(commissionedFilter).collect(Collectors.toList());
        
        if (!commissionedEmployees.isEmpty()) {
            Commissioned emp = (Commissioned) commissionedEmployees.get(getEmployeeIndex(input, commissionedEmployees));
            LocalDate date = ConsoleUtils.readDateInput(input);
            Double value = ConsoleUtils.readDoubleInput(input, "Enter sale value:");
            
            SaleReport saleReport = new SaleReport(date, value);
            emp.getSaleReports().add(saleReport);
            System.out.println("Sale Report added");
        } else {
            System.out.println("There are no commisioned employees to add sale report");
        }
    }

    public static void addServiceTax(Scanner input, List<Employee> employeeList) {
        Predicate<Employee> unionFilter = employee -> employee.getUnionMember() != null && employee.getUnionMember().isActive();
        List<Employee> unionEmployees = employeeList.stream().filter(unionFilter).collect(Collectors.toList());
        
        if (!unionEmployees.isEmpty()) {
            Employee emp = unionEmployees.get(getEmployeeIndex(input, unionEmployees));
            LocalDate date = ConsoleUtils.readDateInput(input);
            Double value = ConsoleUtils.readDoubleInput(input, "Enter tax value");

            ServiceTax serviceTax = new ServiceTax(date, value);
            emp.getUnionMember().getServiceTaxes().add(serviceTax);
            System.out.println("Service Tax added");
        } else {
            System.out.println("There are no Union members to add service tax");
        }
    }

    public static void editEmployee(Scanner input, List<Employee> employeeList) {
        int idx = getEmployeeIndex(input, employeeList);
        Employee emp = employeeList.get(idx);

        System.out.println("Select Attribute to edit");
        System.out.println("[1] Name");
        System.out.println("[2] Address");
        System.out.println("[3] Type");
        System.out.println("[4] Payment Method");
        System.out.println("[5] Union Member Status");
        System.out.println("[6] Union Monthly Tax");
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
                    Double commissionRate = ConsoleUtils.readDoubleInput(input, "Enter the commission rate:");
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
            default:
                break;
        }
    }

    private static int getEmployeeIndex(Scanner input, List<Employee> employeeList) {
        int i = 1, index = 0, empListSize = employeeList.size();
        System.out.println("\nChoose Employee [1-" + empListSize + "]:");
        for (Employee e : employeeList) {
            System.out.println("#" + i + " " + e.printBasicInfo());
            i++;
        }
        while (index <= 0 || index > empListSize) {
            index = ConsoleUtils.readIntInput(input, "");
        }
        return index - 1;
    }
}
