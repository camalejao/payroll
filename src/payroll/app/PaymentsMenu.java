package payroll.app;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Scanner;

import payroll.app.util.ConsoleUtils;
import payroll.model.employee.Employee;
import payroll.model.payments.PaymentInfo;
import payroll.model.payments.PaymentMethod;

public class PaymentsMenu {
    
    public static PaymentInfo getPaymentInfoInput(Scanner input, String schedule) {
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

        return new PaymentInfo(bank, agency, account, schedule, paymentMethod);
    }

    public static void payroll(Scanner input, List<Employee> employeeList) {
        System.out.println("Please insert START date:");
        LocalDate start = ConsoleUtils.readDateInput(input);
        System.out.println("Please insert END date:");
        LocalDate end = ConsoleUtils.readDateInput(input);

        LocalDate currentDate = start;
        int diff = Period.between(start, end.plusDays(1)).getDays();
        System.out.println(diff);
        long diff2 = ChronoUnit.DAYS.between(start, end.plusDays(1));
        System.out.println(diff2);
        int fridayCounter = 0;

        for (int i = 0; i < diff2; i++) {
            currentDate = start.plusDays(i);
            System.out.println("Current Date: " + currentDate.toString());

            if (currentDate.isEqual(getLastWorkingDateOfMonth(currentDate.with(
                TemporalAdjusters.lastDayOfMonth())))) {
                System.out.println("Last day of month, pay salaried employees");
                // TODO: process payment to salaried employees
            }

            if (currentDate.getDayOfWeek() == DayOfWeek.FRIDAY) {
                System.out.println("Friday, pay hourly employees");
                // TODO: process payment to hourly employees

                if (fridayCounter % 2 == 0) {
                    System.out.println("Friday #" + fridayCounter + ", pay commissioned employees");
                    // TODO: process payment to commissioned employees
                }
                fridayCounter += 1;
            }
        }
    }

    private static LocalDate getLastWorkingDateOfMonth(LocalDate lastDayOfMonth) {
        LocalDate lastWorkingDateOfMonth;
        switch (lastDayOfMonth.getDayOfWeek()) {
            case SATURDAY:
                lastWorkingDateOfMonth = lastDayOfMonth.minusDays(1);
                break;
            case SUNDAY:
                lastWorkingDateOfMonth = lastDayOfMonth.minusDays(2);
                break;
            default:
                lastWorkingDateOfMonth = lastDayOfMonth;
                break;
        }
        return lastWorkingDateOfMonth;
    }
}
