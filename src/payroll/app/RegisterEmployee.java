package payroll.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import payroll.model.employee.Employee;
import payroll.model.payments.PaymentMethod;
import payroll.model.payments.PaymentSchedule;
import payroll.model.payments.Wage;
import payroll.model.payments.WageType;
import payroll.model.union.UnionMember;

public class RegisterEmployee {
    public static void main(String[] args) {
        String name, address, answerYorN = "Y";
        Double wageValue, commissionRate = 0.0, unionFee;
        PaymentMethod paymentMethod;
        WageType wageType;
        int answer, i = 1;

        Employee employee;
        Wage wage;
        UnionMember unionMember = null;
        PaymentSchedule paymentSchedules = new PaymentSchedule();
        String schedule = "";
        List<Employee> employeeList = new ArrayList<Employee>();
    
        Scanner input = new Scanner(System.in);

        while (answerYorN.equalsIgnoreCase("Y")) {

            System.out.println("\nEnter the employee's name:");
            name = input.nextLine();
            System.out.println();
    
            System.out.println("Enter the address:");
            address = input.nextLine();
            System.out.println();
    
            System.out.println("Select payment method (enter 1, 2 or 3):");
            for (PaymentMethod pm : PaymentMethod.values()) {
                System.out.println("[" + i + "]. " + pm.getMethodDescription());
                i++;
            }
            i = 1;
            answer = input.nextInt();
            paymentMethod = PaymentMethod.values()[answer - 1];
            System.out.println();
            
            System.out.println("Select wage type (enter 1, 2 or 3):");
            for (WageType wt : WageType.values()) {
                System.out.println("[" + i + "]. " + wt.getWageDescription());
                i++;
            }
            i = 1;
            answer = input.nextInt();
            wageType = WageType.values()[answer - 1];
            schedule = paymentSchedules.getOptions().get(answer - 1);
            System.out.println();
    
            if (wageType == WageType.COMMISSIONED) {
                System.out.println("Enter commission rate:");
                commissionRate = input.nextDouble();
                System.out.println();
            }
    
            System.out.println("Enter wage value (per hour or fixed monthly amount):");
            wageValue = input.nextDouble();
            input.nextLine(); // to read the 'Enter'
            System.out.println();
    
            wage = new Wage(wageType, paymentMethod, wageValue, commissionRate, schedule);
    
            System.out.println("Is the employee a union member? (Y/N)");
            answerYorN = input.nextLine();
            System.out.println("You answered: " + answerYorN);
            System.out.println();
            
            if (answerYorN.equalsIgnoreCase("Y")) {
                System.out.println("Enter the monthly union fee:");
                unionFee = input.nextDouble();
                input.nextLine(); // to read the 'Enter'
                System.out.println();
                unionMember = new UnionMember(UUID.randomUUID(), true, unionFee);
            }
            
            employee = new Employee(UUID.randomUUID(), name, address, wage, unionMember);
            employeeList.add(employee);
            
            System.out.println("\nNew employee registered!");
            System.out.println(employee.toString());
            
            System.out.println("\n\nContinue registering new employees? (Y/N)");
            answerYorN = input.nextLine();
            
            // reset optional variables
            commissionRate = 0.0;
            unionMember = null;
        }
        
        i = 1;
        System.out.println("\n\nEmployee List:");
        for (Employee e : employeeList) {
            System.out.println("\nEmployee #" + i);
            System.out.println(e.toString());
            System.out.println("\n=======================================================");
            i++;
        }

        input.close();
    }
    
}
