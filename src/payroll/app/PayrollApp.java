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
            System.out.println("[0] Exit");

            option = input.nextInt();
            input.nextLine();

            switch (option) {
                case 1:
                    employeeList.add(RegisterEmployee.registration(input, paymentSchedules));
                    ConsoleUtils.pressEnterToContinue(input);
                    break;
                
                case 2:
                    printEmployees(employeeList);
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
}
