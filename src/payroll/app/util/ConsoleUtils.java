package payroll.app.util;

import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleUtils {
    public static void clear() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }

    public static void pressEnterToContinue(Scanner input) {
        System.out.println("\nPress ENTER to continue");
        input.nextLine();
    }

    public static LocalDate getDateInput(Scanner input) {
        System.out.println("Enter day number:");
        int day = input.nextInt();
        System.out.println("Enter month number:");
        int month = input.nextInt();
        System.out.println("Enter year number:");
        int year = input.nextInt();

        return LocalDate.of(year, month, day);
    }
}
