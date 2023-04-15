package thirdparty.paymentgateway;

public class AdultTicket {
        private static int price;
        public AdultTicket() {
            this.price = 20; // price for adult ticket is 20
        }
        public static int getPrice() {
            return price;
        }

    }

