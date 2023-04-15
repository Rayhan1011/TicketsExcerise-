package thirdparty.paymentgateway;

import thirdparty.seatbooking.SeatReservationService;
import thirdparty.seatbooking.SeatReservationServiceImpl;

import java.util.Scanner;

public class NumberOfTicketsAB {

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


//creating a scanner object to read user input
        Scanner scanner = new Scanner(System.in);
//entering prompt for number of tickets user wants to purchase
        System.out.println("how much do you want to buy");
//reading the user input
        int numberOfTickets = scanner.nextInt();
//Checking if number of tickets meet the 20 ticket rule
        if (numberOfTickets <= 0 || numberOfTickets > 20) {
            System.out.println("Sorry. Only maximum 20 tickets can be purchased at a time");
            return;
        }


//entering prompt for number of infants attending
        System.out.println("How many infants will be attending?");
//reading the user input
        int numberOfInfants = scanner.nextInt();
//entering prompt for number of Adults attending
        System.out.println("How many Adults will be attending?");
//reading the user input
        int numberOfAdults = scanner.nextInt();
//Checking if the number of adults is atleast equal to number of infants entered
        if (!(numberOfInfants <= numberOfAdults)) {
            System.out.println("Sorry. You don't have enough adults with you");
            return;
        }

//entering prompt for number of children attending
        System.out.println("How many childs will be attending?");
//reading the user input
        int numberOfChilds = scanner.nextInt();
//Making sure the number of tickets is equal to amount attending
        if (!(numberOfTickets == numberOfAdults + numberOfChilds + numberOfInfants)) {
            System.out.println("Sorry. You are buying more than " + numberOfTickets + " tickets");
            return;
        }


// displaying to the user how much they have requested
        System.out.println("You have requested " + numberOfInfants + " Infant tickets, " + numberOfChilds + " Child tickets and " + numberOfAdults + " Adult tickets");
        System.out.println("");

// Creating ticket objects
        InfantTicket infantTicket = new InfantTicket();
        ChildTicket childTicket = new ChildTicket();
        AdultTicket adultTicket = new AdultTicket();
        int totalCost = numberOfInfants * infantTicket.getPrice() +
                numberOfAdults * adultTicket.getPrice() +
                numberOfChilds * childTicket.getPrice();
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
        int numberOfSeatsToReserve = numberOfAdults + numberOfChilds;
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










