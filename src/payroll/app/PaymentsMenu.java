package payroll.app;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

import payroll.app.util.ConsoleUtils;
import payroll.model.employee.Employee;
import payroll.model.payments.Paycheck;
import payroll.model.payments.PaymentInfo;
import payroll.model.payments.PaymentMethod;

public class PaymentsMenu {
    
    public static PaymentInfo getPaymentInfoInput(Scanner input) {
        int i = 1;
        System.out.println("Select payment method (enter 1, 2 or 3):");
        for (PaymentMethod pm : PaymentMethod.values()) {
            System.out.println("[" + i + "]. " + pm.getMethodDescription());
            i++;
        }
        int answer = input.nextInt();
        PaymentMethod paymentMethod = PaymentMethod.values()[answer - 1];
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

        input.nextLine(); // new line

        return new PaymentInfo(bank, agency, account, paymentMethod, null);
    }

    public static void payroll(Scanner input, List<Employee> employeeList) {
        Paycheck paycheck;

        System.out.println("\nPlease insert START date:");
        LocalDate start = ConsoleUtils.readDateInput(input);
        System.out.println("\nPlease insert END date:");
        LocalDate end = ConsoleUtils.readDateInput(input);

        LocalDate currentDate = start;
        long diff = ChronoUnit.DAYS.between(start, end.plusDays(1));
        System.out.println(diff + " day(s) of payments will be processed");
        
        int weekCounter = -1;
        DayOfWeek startingDay = start.getDayOfWeek();

        for (int i = 0; i < diff; i++) {
            currentDate = start.plusDays(i);
            if (currentDate.getDayOfWeek() == startingDay) weekCounter++;

            for (Employee e : employeeList) {
                if (e.getPaymentInfo().isPaymentDay(weekCounter, currentDate)) {
                    paycheck = e.processPayment(currentDate);
                    System.out.println(paycheck.toString());
                }
            }
        }
    }
}
