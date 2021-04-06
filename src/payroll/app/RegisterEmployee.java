package payroll.app;

import java.util.Scanner;
import java.util.UUID;

import payroll.model.employee.Commissioned;
import payroll.model.employee.Employee;
import payroll.model.employee.Hourly;
import payroll.model.employee.Salaried;
import payroll.model.payments.PaymentInfo;
import payroll.model.payments.PaymentMethod;
import payroll.model.payments.PaymentSchedule;
import payroll.model.union.UnionMember;

public class RegisterEmployee {
    public static Employee registration(Scanner input, PaymentSchedule paymentSchedules) {
        
        Employee employee;
        PaymentInfo paymentInfo = null;
        UnionMember unionMember = null;
        PaymentMethod paymentMethod;
        
        int answer, i = 1;
        String schedule = "", answerYorN;

        System.out.println("\nEnter the employee's name:");
        String name = input.nextLine();
        System.out.println();

        System.out.println("Enter the address:");
        String address = input.nextLine();
        System.out.println();

        System.out.println("What is the type of employee? (enter 1, 2 or 3)");
        System.out.printf("[1] Hourly\n[2] Salaried\n[3] Commissioned\n");
        answer = input.nextInt();
        System.out.println();

        if (answer == 1) {
            
            System.out.println("Enter the hourly rate/wage:");
            Double hourlyRate = input.nextDouble();
            input.nextLine(); // to read the 'Enter'
            System.out.println();

            employee = new Hourly(UUID.randomUUID(), name, address, unionMember, paymentInfo, hourlyRate);

        } else if (answer == 2) {
            
            System.out.println("Enter the salary:");
            Double salary = input.nextDouble();
            input.nextLine(); // to read the 'Enter'
            System.out.println();
            
            employee = new Salaried(UUID.randomUUID(), name, address, unionMember, paymentInfo, salary);

        } else if (answer == 3) {
            
            input.nextLine();
            System.out.println("Enter the salary:");
            Double salary = input.nextDouble();
            System.out.println();

            System.out.println("Enter the commission rate:");
            Double commissionRate = input.nextDouble();
            input.nextLine(); // to read the 'Enter'
            System.out.println();

            employee = new Commissioned(UUID.randomUUID(), name, address, unionMember, paymentInfo, salary, commissionRate);

        } else {
            
            employee = new Employee(UUID.randomUUID(), name, address, unionMember, paymentInfo);
        }

        System.out.println("Select payment method (enter 1, 2 or 3):");
        for (PaymentMethod pm : PaymentMethod.values()) {
            System.out.println("[" + i + "]. " + pm.getMethodDescription());
            i++;
        }
        i = 1;
        answer = input.nextInt();
        paymentMethod = PaymentMethod.values()[answer - 1];
        schedule = paymentSchedules.getOptions().get(answer - 1);
        System.out.println();


        System.out.println("Enter the bank number:");
        int bank = input.nextInt();
        System.out.println();

        System.out.println("Enter the agency number:");
        int agency = input.nextInt();
        System.out.println();

        System.out.println("Enter the account number:");
        int account = input.nextInt();
        System.out.println();

        paymentInfo = new PaymentInfo(bank, agency, account, schedule, paymentMethod);
        employee.setPaymentInfo(paymentInfo);

        input.nextLine();
        System.out.println("Is the employee a union member? (Y/N)");
        answerYorN = input.nextLine();
        System.out.println();
        
        if (answerYorN.equalsIgnoreCase("Y")) {
            System.out.println("Enter the monthly union fee:");
            Double unionFee = input.nextDouble();
            input.nextLine(); // to read the 'Enter'
            System.out.println();
            unionMember = new UnionMember(UUID.randomUUID(), true, unionFee);
            employee.setUnionMember(unionMember);
        }
        
        System.out.println("\nNew employee registered!");
        System.out.println(employee.toString());

        return employee;
    }
}
