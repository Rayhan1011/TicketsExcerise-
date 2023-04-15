package thirdparty.paymentgateway;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

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

}
