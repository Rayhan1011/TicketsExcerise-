package thirdparty.paymentgateway;

import thirdparty.seatbooking.SeatReservationService;
import thirdparty.seatbooking.SeatReservationServiceImpl;

import java.util.Scanner;

public class TicketExercise {

    public TicketExercise(Scanner scanner, TicketPaymentService paymentService, SeatReservationService reservationService) {

    }

    public static void main(String[] args) {

        // AdultTicket class
        class AdultTicket {
            private int price;

            public AdultTicket() {
                this.price = 20; // price for adult ticket is 20
            }

            public int getPrice() {
                return price;
            }

        }

        // InfantTicket class
        class InfantTicket {
            private int price;

            public InfantTicket() {
                this.price = 0; // price for infant ticket is 0
            }

            public int getPrice() {
                return price;
            }

        }

        // ChildTicket class
        class ChildTicket {
            private int price;

            public ChildTicket() {
                this.price = 10; // price for child ticket is 10
            }

            public int getPrice() {
                return price;
            }
        }

        // Creating a scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Prompting the user for the number of tickets they want to purchase
        System.out.println("How many tickets do you want to buy?");

        // Reading the user input
        int numberOfTickets = scanner.nextInt();

        // Checking if the number of tickets meets the 20 ticket rule
        if (numberOfTickets <= 0 || numberOfTickets > 20) {
            System.out.println("Sorry. Only a maximum of 20 tickets can be purchased at a time");
            return;
        }


        // Prompting the user for the number of adults attending
        System.out.println("How many adults will be attending?");

        // Reading the user input
        int numberOfAdults = scanner.nextInt();

        // Checking if there is at least one adult to purchase tickets
        if (numberOfAdults <= 0) {
            System.out.println("Sorry, at least one adult is required to purchase tickets.");
            return;
        }

        // Prompting the user for the number of infants attending
        System.out.println("How many infants will be attending?");

        // Reading the user input
        int numberOfInfants = scanner.nextInt();

        // Checking if the number of adults is at least equal to the number of infants entered
        if (!(numberOfInfants <= numberOfAdults)) {
            System.out.println("Sorry. You don't have enough adults with you");
            return;
        }


        // Prompting the user for the number of children attending
        System.out.println("How many children will be attending?");

        // Reading the user input
        int numberOfChildren = scanner.nextInt();

        // Making sure the number of tickets is equal to the number of people attending
        if (( numberOfAdults + numberOfChildren + numberOfInfants > 20)) {
            System.out.println("Sorry. You are buying more than 20 tickets");
            return;
        }

        // Displaying to the user how many tickets they have requested
        System.out.println("You have requested " + numberOfInfants + " infant tickets, " + numberOfChildren + " child tickets, and " + numberOfAdults + " adult tickets");
        System.out.println("");

        // Creating ticket objects
        InfantTicket infantTicket = new InfantTicket();
        ChildTicket childTicket = new ChildTicket();
        AdultTicket adultTicket = new AdultTicket();
        int totalCost = numberOfInfants * infantTicket.getPrice() +
                numberOfAdults * adultTicket.getPrice() +
                numberOfChildren * childTicket.getPrice();
        System.out.println("Total cost of tickets will be: " + totalCost);


// Payment request to TicketPaymentService
//code goes here for this
// Payment request to TicketPaymentService
        // Create a new instance of the TicketPaymentService
        TicketPaymentService paymentService = new TicketPaymentServiceImpl();

        // Make the payment
        long accountId = 1L; // Replace with the account ID of the user making the payment
        boolean paymentStatus = paymentService.makePayment(accountId, totalCost);

        //Adding condition to make sure payment has gone though
        if (!paymentStatus) {
            System.out.println("Payment failed. Please try again.");
            return;
        }

//now adding adult tickets and child tickets to see how many seats to reserve
        int numberOfSeatsToReserve = numberOfAdults + numberOfChildren;
        System.out.println("We will look to reserve for you: " + numberOfSeatsToReserve + " seats");

// Seat reservation request to SeatReservationService
//code goes here
        SeatReservationService reservationService = new SeatReservationServiceImpl();

        // Make the reservation satus
        boolean reservedStatus = reservationService.reserveSeat(accountId, numberOfSeatsToReserve);
        //Adding condition to make sure Seat Reservation has gone though
        if (!reservedStatus) {
            System.out.println("Seat Reservation failed. Please try again.");
            return;
        }
        System.out.println("your ticket has been purchased and your seat has been reserved");
        ;

//

//


    }


}










