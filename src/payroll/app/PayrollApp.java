package payroll.app;

import java.util.Scanner;

import payroll.app.util.ConsoleUtils;
import payroll.model.Company;

public class PayrollApp {
    public static void main(String[] args) {
        int option = 1;
        Scanner input = new Scanner(System.in);

        Company company = new Company();

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
                    company.addEmployee(EmployeeMenu.registerEmployee(input, company.getPaymentSchedules()));
                    ConsoleUtils.pressEnterToContinue(input);
                    break;
                
                case 2:
                    if (!company.isEmployeeListEmpty()) {
                        EmployeeMenu.printEmployees(company.getEmployees());
                    } else {
                        System.out.println("No employees registered to be listed");
                    }
                    ConsoleUtils.pressEnterToContinue(input);
                    break;
                    
                case 3:
                    if (!company.isEmployeeListEmpty()) {
                        company.removeEmployee(EmployeeMenu.removeEmployee(input, company.getEmployees()));
                    } else {
                        System.out.println("No employees registered to be removed");
                    }
                    ConsoleUtils.pressEnterToContinue(input);
                    break;
                
                case 4:
                    if (!company.isEmployeeListEmpty()) {
                        EmployeeMenu.addTimecard(input, company.getHourlyEmployees());
                    } else {
                        System.out.println("No employees registered to add it");
                    }
                    ConsoleUtils.pressEnterToContinue(input);
                    break;
                
                case 5:
                    if (!company.isEmployeeListEmpty()) {
                        EmployeeMenu.addSaleReport(input, company.getCommissionedEmployees());
                    } else {
                        System.out.println("No employees registered to add it");
                    }
                    ConsoleUtils.pressEnterToContinue(input);
                    break;
                
                case 6:
                    if (!company.isEmployeeListEmpty()) {
                        EmployeeMenu.addServiceTax(input, company.getUnionMemberEmployees());
                    } else {
                        System.out.println("No employees registered to add it");
                    }
                    ConsoleUtils.pressEnterToContinue(input);
                    break;
                
                case 7:
                    if (!company.isEmployeeListEmpty()) {
                        EmployeeMenu.editEmployee(input, company.getEmployees());
                    } else {
                        System.out.println("No employees registered to edit");
                    }
                    ConsoleUtils.pressEnterToContinue(input);
                    break;
                
                case 8:
                    if (!company.isEmployeeListEmpty()) {
                        PaymentsMenu.payroll(input, company.getEmployees());
                    } else {
                        System.out.println("No employees registered to pay");
                    }
                    ConsoleUtils.pressEnterToContinue(input);
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
