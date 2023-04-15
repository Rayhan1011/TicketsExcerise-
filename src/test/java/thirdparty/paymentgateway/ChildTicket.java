package thirdparty.paymentgateway;

public class ChildTicket {
    private static int price;
    public ChildTicket() {
        this.price = 10; // price for child ticket is 10
    }
    public static int getPrice() {
        return price;
    }
}
