package thirdparty.paymentgateway;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import thirdparty.seatbooking.SeatReservationService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mockStatic;

public class TicketExerciseTest {
    @Test
    public void testAdultTicketPrice() {
        AdultTicket adultTicket = new AdultTicket();
        Assertions.assertEquals(20, adultTicket.getPrice());
    }

    @Test
    public void testInfantTicketPrice() {
        InfantTicket infantTicket = new InfantTicket();
        Assertions.assertEquals(0, infantTicket.getPrice());
    }

    @Test
    public void testChildTicketPrice() {
        ChildTicket childTicket = new ChildTicket();
        Assertions.assertEquals(10, childTicket.getPrice());
    }

    @Test
    public void testNumberOfTickets() {
        String input = "30";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        int numberOfTickets = scanner.nextInt();
        if (numberOfTickets <= 0 || numberOfTickets > 20) {
            System.out.println("Sorry. Only maximum 20 tickets can be purchased at a time");
            return;
        }
        assertEquals(30, numberOfTickets);
    }

    @Test
    void testTicketPurchaseAndReservation() {
        // Setting up scanner mock for user input
        Scanner scanner = Mockito.mock(Scanner.class);
        Mockito.when(scanner.nextInt())
                .thenReturn(2)  // number of infants
                .thenReturn(2)  // number of adults
                .thenReturn(1); // number of children

        // Setting up TicketPaymentService mock
        TicketPaymentService paymentService = Mockito.mock(TicketPaymentService.class);
        Mockito.when(paymentService.makePayment(Mockito.anyLong(), Mockito.anyInt()))
                .thenReturn(true);

        // Setting up SeatReservationService mock
        SeatReservationService reservationService = Mockito.mock(SeatReservationService.class);
        Mockito.when(reservationService.reserveSeat(Mockito.anyLong(), Mockito.anyInt()))
                .thenReturn(true);

        // Mocking static methods
        try (MockedStatic<AdultTicket> adultTicketMockedStatic = mockStatic(AdultTicket.class);
             MockedStatic<ChildTicket> childTicketMockedStatic = mockStatic(ChildTicket.class);
             MockedStatic<InfantTicket> infantTicketMockedStatic = mockStatic(InfantTicket.class)) {

            // Mocking AdultTicket, ChildTicket and InfantTicket
            adultTicketMockedStatic.when(AdultTicket::getPrice).thenReturn(20);
            childTicketMockedStatic.when(ChildTicket::getPrice).thenReturn(10);
            infantTicketMockedStatic.when(InfantTicket::getPrice).thenReturn(0);

            // Running the code
            new TicketExercise(scanner, paymentService, reservationService).purchaseAndReserveTicket();

            // Asserting the results
            Mockito.verify(scanner, Mockito.times(6)).nextInt();
            Mockito.verify(paymentService, Mockito.times(1)).makePayment(Mockito.anyLong(), Mockito.eq(60));
            Mockito.verify(reservationService, Mockito.times(1)).reserveSeat(Mockito.anyLong(), Mockito.eq(4));
        }
    }

    @Test
    void testNumberOfTicketsLimit() {
        // Setting up scanner mock for user input
        Scanner scanner = Mockito.mock(Scanner.class);
        Mockito.when(scanner.nextInt())
                .thenReturn(25);  // number of tickets

        // Setting up TicketPaymentService and SeatReservationService mocks
        TicketPaymentService paymentService = Mockito.mock(TicketPaymentService.class);
        SeatReservationService reservationService = Mockito.mock(SeatReservationService.class);

        // Running the code
        TicketExercise ticketExercise = new TicketExercise(scanner, paymentService, reservationService);
        ticketExercise.purchaseAndReserveTicket();

        // Asserting the error message
        Assertions.assertEquals("Sorry. Only maximum 20 tickets can be purchased at a time", ticketExercise.getErrorMsg());
    }

    @Test
    void testNotEnoughAdults() {
        // Setting up scanner mock for user input
        Scanner scanner = Mockito.mock(Scanner.class);
        Mockito.when(scanner.nextInt())
                .thenReturn(2)  // number of infants
                .thenReturn(1)  // number of adults
                .thenReturn(1); // number of children

        // Setting up TicketPaymentService and SeatReservationService mocks
        TicketPaymentService paymentService = Mockito.mock(TicketPaymentService.class);
        SeatReservationService reservationService = Mockito.mock(SeatReservationService.class);

        // Running the code
        TicketExercise ticketExercise = new TicketExercise(scanner, paymentService, reservationService);
        ticketExercise.purchaseAndReserveTicket();
    }

}
