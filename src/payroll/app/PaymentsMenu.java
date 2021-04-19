package payroll.app;

import java.util.Scanner;

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
}
