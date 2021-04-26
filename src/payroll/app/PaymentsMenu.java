package payroll.app;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import payroll.app.util.ConsoleUtils;
import payroll.model.employee.Commissioned;
import payroll.model.employee.Employee;
import payroll.model.employee.Hourly;
import payroll.model.employee.Salaried;
import payroll.model.payments.Paycheck;
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
        Paycheck paycheck;

        System.out.println("\nPlease insert START date:");
        LocalDate start = ConsoleUtils.readDateInput(input);
        System.out.println("\nPlease insert END date:");
        LocalDate end = ConsoleUtils.readDateInput(input);

        LocalDate currentDate = start;
        long diff = ChronoUnit.DAYS.between(start, end.plusDays(1));
        System.out.println(diff);
        int fridayCounter = 0;

        for (int i = 0; i < diff; i++) {
            currentDate = start.plusDays(i);

            if (currentDate.isEqual(getLastWorkingDateOfMonth(currentDate.with(
                TemporalAdjusters.lastDayOfMonth())))) {
                System.out.println("\n\nLast working day of month (" + currentDate.toString() + "), pay salaried employees");
                Predicate<Employee> salariedFilter = employee -> employee instanceof Salaried;
                List<Employee> salariedEmployees = employeeList.stream().filter(salariedFilter).collect(Collectors.toList());
                if (!salariedEmployees.isEmpty()) {
                    for (Employee e : salariedEmployees) {
                        paycheck = e.processPayment(currentDate);
                        System.out.println(paycheck.toString());
                    }
                } else {
                    System.out.println("No hourly employees!");
                }
            }

            if (currentDate.getDayOfWeek() == DayOfWeek.FRIDAY) {
                System.out.println("\n\nFriday (" + currentDate.toString() + "), pay hourly employees");
                Predicate<Employee> hourlyFilter = employee -> employee instanceof Hourly;
                List<Employee> hourlyEmployees = employeeList.stream().filter(hourlyFilter).collect(Collectors.toList());
                if (!hourlyEmployees.isEmpty()) {
                    for (Employee e : hourlyEmployees) {
                        paycheck = e.processPayment(currentDate);
                        System.out.println(paycheck.toString());
                    }
                } else {
                    System.out.println("No hourly employees!");
                }

                if (fridayCounter % 2 == 0) {
                    System.out.println("\n\nFriday #" + fridayCounter + " (" + currentDate.toString() + "), pay commissioned employees");
                    Predicate<Employee> commissionedFilter = employee -> employee instanceof Commissioned;
                    List<Employee> commissionedEmployees = employeeList.stream().filter(commissionedFilter).collect(Collectors.toList());
                    if (!commissionedEmployees.isEmpty()) {
                        for (Employee e : commissionedEmployees) {
                            paycheck = e.processPayment(currentDate);
                            System.out.println(paycheck.toString());
                        }
                    } else {
                        System.out.println("No commissioned employees!");
                    }
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
