package payroll.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import payroll.app.util.ConsoleUtils;
import payroll.model.employee.Employee;
import payroll.model.payments.PaymentSchedule;

public class PayrollApp {
    public static void main(String[] args) {
        int option = 1;
        Scanner input = new Scanner(System.in);

        List<Employee> employeeList = new ArrayList<Employee>();
        PaymentSchedule paymentSchedules = new PaymentSchedule();

        while (option != 0) {
            System.out.println("Payroll App Menu");
            System.out.println("[1] Register Employee");
            System.out.println("[2] List Employees");
            System.out.println("[3] Remove Employee");
            System.out.println("[4] Add Timecard");
            System.out.println("[5] Add Sale Report");
            System.out.println("[6] Add Service Tax");
            System.out.println("[0] Exit");

            option = input.nextInt();
            input.nextLine();

            switch (option) {
                case 1:
                    employeeList.add(EmployeeMenu.registerEmployee(input, paymentSchedules));
                    ConsoleUtils.pressEnterToContinue(input);
                    break;
                
                case 2:
                    if (!employeeList.isEmpty()) {
                        EmployeeMenu.printEmployees(employeeList);
                        ConsoleUtils.pressEnterToContinue(input);
                    } else {
                        System.out.println("No employees registered to be listed");
                    }
                    break;
                    
                case 3:
                    if (!employeeList.isEmpty()) {
                        EmployeeMenu.removeEmployee(input, employeeList);
                        ConsoleUtils.pressEnterToContinue(input);
                    } else {
                        System.out.println("No employees registered to be removed");
                    }
                    break;
                
                case 4:
                    if (!employeeList.isEmpty()) {
                        EmployeeMenu.addTimecard(input, employeeList);
                        ConsoleUtils.pressEnterToContinue(input);
                    } else {
                        System.out.println("No employees registered to add it");
                    }
                    break;
                
                case 5:
                    if (!employeeList.isEmpty()) {
                        EmployeeMenu.addSaleReport(input, employeeList);
                        ConsoleUtils.pressEnterToContinue(input);
                    } else {
                        System.out.println("No employees registered to add it");
                    }
                    break;
                
                case 6:
                    if (!employeeList.isEmpty()) {
                        EmployeeMenu.addServiceTax(input, employeeList);
                        ConsoleUtils.pressEnterToContinue(input);
                    } else {
                        System.out.println("No employees registered to add it");
                    }
                    break;
                
                default:
                    break;
            }
            ConsoleUtils.clear();
        }
        input.close();
        System.out.println("Bye!");
    }

    
}
