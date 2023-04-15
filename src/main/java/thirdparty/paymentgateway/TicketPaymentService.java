package thirdparty.paymentgateway;

public interface TicketPaymentService {

    boolean makePayment(long accountId, int totalAmountToPay);


}
