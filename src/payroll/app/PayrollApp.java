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
            System.out.println("[7] Edit Employee");
            System.out.println("[8] Run Payroll");
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
                    } else {
                        System.out.println("No employees registered to be listed");
                    }
                    ConsoleUtils.pressEnterToContinue(input);
                    break;
                    
                case 3:
                    if (!employeeList.isEmpty()) {
                        EmployeeMenu.removeEmployee(input, employeeList);
                    } else {
                        System.out.println("No employees registered to be removed");
                    }
                    ConsoleUtils.pressEnterToContinue(input);
                    break;
                
                case 4:
                    if (!employeeList.isEmpty()) {
                        EmployeeMenu.addTimecard(input, employeeList);
                    } else {
                        System.out.println("No employees registered to add it");
                    }
                    ConsoleUtils.pressEnterToContinue(input);
                    break;
                
                case 5:
                    if (!employeeList.isEmpty()) {
                        EmployeeMenu.addSaleReport(input, employeeList);
                    } else {
                        System.out.println("No employees registered to add it");
                    }
                    ConsoleUtils.pressEnterToContinue(input);
                    break;
                
                case 6:
                    if (!employeeList.isEmpty()) {
                        EmployeeMenu.addServiceTax(input, employeeList);
                    } else {
                        System.out.println("No employees registered to add it");
                    }
                    ConsoleUtils.pressEnterToContinue(input);
                    break;
                
                case 7:
                    if (!employeeList.isEmpty()) {
                        EmployeeMenu.editEmployee(input, employeeList);
                    } else {
                        System.out.println("No employees registered to edit");
                    }
                    ConsoleUtils.pressEnterToContinue(input);
                
                case 8:
                    PaymentsMenu.payroll(input, employeeList);
                    // if (!employeeList.isEmpty()) {
                    //     PaymentsMenu.payroll(input, employeeList);
                    // } else {
                    //     System.out.println("No employees registered to pay");
                    // }
                    ConsoleUtils.pressEnterToContinue(input);

                default:
                    break;
            }
            ConsoleUtils.clear();
        }
        input.close();
        System.out.println("Bye!");
    }

    
}
