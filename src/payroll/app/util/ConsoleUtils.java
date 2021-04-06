package payroll.app.util;

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
}
