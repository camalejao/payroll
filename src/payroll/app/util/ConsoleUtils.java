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

    public static LocalDate readDateInput(Scanner input) {
        System.out.println("Enter day number:");
        int day = input.nextInt();
        System.out.println("Enter month number:");
        int month = input.nextInt();
        System.out.println("Enter year number:");
        int year = input.nextInt();
        input.nextLine(); // enter

        return LocalDate.of(year, month, day);
    }

    public static String readStringInput(Scanner input, String message) {
        if (!message.isEmpty()) System.out.println(message);
        String str = input.nextLine();
        System.out.println();
        return str;
    }

    public static Double readDoubleInput(Scanner input, String message) {
        if (!message.isEmpty()) System.out.println(message);
        Double value = input.nextDouble();
        input.nextLine(); // catch new line/enter
        System.out.println();
        return value;
    }

    public static int readIntInput(Scanner input, String message) {
        if (!message.isEmpty()) System.out.println(message);
        int value = input.nextInt();
        input.nextLine(); // catch new line/enter
        System.out.println();
        return value;
    }

    public static boolean confirmation(Scanner input, String message) {
        System.out.println(message + " [Y/N]");
        String answer = input.nextLine();
        System.out.println();
        return answer.equalsIgnoreCase("Y");
    }
}
