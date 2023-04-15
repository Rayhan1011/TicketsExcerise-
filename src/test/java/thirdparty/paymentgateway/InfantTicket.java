package thirdparty.paymentgateway;


    class InfantTicket {
        private static int price;
        public InfantTicket() {
            this.price = 0; // price for infant ticket is 0
        }
        public static int getPrice() {
            return price;
        }

    }

