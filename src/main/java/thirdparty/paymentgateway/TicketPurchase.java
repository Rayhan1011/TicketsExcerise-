package thirdparty.paymentgateway;

import java.util.Scanner;

public class TicketPurchase {
    public static void main(String[] args) {
        // Get the number of tickets to buy from the user
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many tickets do you want to buy?");
        String numberOfTickets = scanner.nextLine();
        System.out.println("You want to buy " + numberOfTickets + " tickets.");

        // Create a new instance of the TicketPaymentService
        TicketPaymentService paymentService = new TicketPaymentServiceImpl();

        // Make the payment
        long accountId = 12345L; // Replace with the account ID of the user making the payment
        int totalAmountToPay = Integer.parseInt(numberOfTickets) * 100; // Assume each ticket costs 100 cents
        paymentService.makePayment(accountId, totalAmountToPay);

        // Close the scanner
        scanner.close();
    }
}
