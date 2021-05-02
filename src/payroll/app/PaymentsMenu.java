package payroll.app;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import payroll.app.util.ConsoleUtils;
import payroll.model.employee.Employee;
import payroll.model.payments.Paycheck;
import payroll.model.payments.PaymentInfo;
import payroll.model.payments.PaymentMethod;
import payroll.model.payments.PaymentSchedule;
import payroll.model.payments.PaymentsReport;
import payroll.model.payments.Schedule;

public class PaymentsMenu {
    
    public static PaymentInfo getPaymentInfoInput(Scanner input) {
        int i = 1;
        System.out.println("Select payment method (enter 1, 2 or 3):");
        for (PaymentMethod pm : PaymentMethod.values()) {
            System.out.println("[" + i + "]. " + pm.getMethodDescription());
            i++;
        }
        int answer = input.nextInt();
        PaymentMethod paymentMethod;
        if (answer >= 1 && answer <= 3) {
            paymentMethod = PaymentMethod.values()[answer - 1];
        } else {
            System.out.println("Invalid option. Default is Bank Deposit.");
            paymentMethod = PaymentMethod.BANK_DEPOSIT;
        }
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

    public static List<PaymentsReport> payroll(Scanner input, List<Employee> employeeList) {
        Paycheck paycheck;
        List<Paycheck> paycheckList;
        List<PaymentsReport> paymentReportsList = new ArrayList<>();

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

            paycheckList = new ArrayList<>();

            for (Employee e : employeeList) {
                if (e.getPaymentInfo().isPaymentDay(weekCounter, currentDate)) {
                    paycheck = e.processPayment(currentDate);
                    System.out.println(paycheck.toString());
                    paycheckList.add(paycheck);
                }
            }

            if (!paycheckList.isEmpty()) {
                paymentReportsList.add(new PaymentsReport(paycheckList, currentDate));
            }
        }

        return paymentReportsList;
    }

    public static PaymentSchedule registerNewPaymentSchedule(Scanner input) {
        
        String message = "Select the type of schedule:\n";
        message += "[1] monthly\n[2] weekly 1 (every week)\n[3] weekly 2 (every two weeks)\n";
        int answer = ConsoleUtils.readIntInput(input, message);

        if (answer == 1) {
            message = "Now, enter the day of month.\n";
            message += "It needs to be a number between 1 and 28; if you enter a number out of this range,";
            message += " the selected value will be $ (last working day of the month):";
            int day = ConsoleUtils.readIntInput(input, message);
            
            if (day >= 1 && day <= 28) {
                return new PaymentSchedule(Schedule.MONTHLY, day, null);
            } else {
                return new PaymentSchedule(Schedule.MONTHLY, null, null);
            }

        } else if (answer == 2) {
            message = "Now, select the day of week. (only work days allowed)\n";
            message += "It needs to be a number between 1 and 5, meaning the days from Monday to Friday.\n";
            message += "[1] Monday\n[2] Tuesday\n[3] Wendnesday\n[4] Thursday\n[5] Friday\n";
            int dayOfWeek = ConsoleUtils.readIntInput(input, message);

            if (dayOfWeek >= 1 && dayOfWeek <= 5) {
                return new PaymentSchedule(Schedule.WEEKLY, null, DayOfWeek.of(dayOfWeek));
            } else {
                System.out.println("Invalid option, default will be Monday.");
                return new PaymentSchedule(Schedule.WEEKLY, null, DayOfWeek.of(dayOfWeek));
            }

        } else if (answer == 3) {
            message = "Now, select the day of week. (only work days allowed)\n";
            message += "It needs to be a number between 1 and 5, meaning the days from Monday to Friday.\n";
            message += "[1] Monday\n[2] Tuesday\n[3] Wendnesday\n[4] Thursday\n[5] Friday\n";
            int dayOfWeek = ConsoleUtils.readIntInput(input, message);

            if (dayOfWeek >= 1 && dayOfWeek <= 5) {
                return new PaymentSchedule(Schedule.BIWEEKLY, null, DayOfWeek.of(dayOfWeek));
            } else {
                System.out.println("Invalid option, default will be Monday.");
                return new PaymentSchedule(Schedule.BIWEEKLY, null, DayOfWeek.of(dayOfWeek));
            }
            
        } else {
            System.out.println("Invalid option, default will be 'monthly 15'.");
            return new PaymentSchedule(Schedule.MONTHLY, 15, null);
        }
    }
}
