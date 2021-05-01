package payroll.app.util;

import java.util.List;
import java.util.Scanner;

import payroll.model.employee.Employee;

public class EmployeeUtils {
    public static int getEmployeeIndex(Scanner input, List<Employee> employeeList) {
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
