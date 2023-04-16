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



}
