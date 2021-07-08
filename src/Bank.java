public class Bank implements Observer {

    public void update(Atm.Stock_alert message) {
        processMessage(message);
    }

    private void processMessage(Atm.Stock_alert message) {
        if (message == Atm.Stock_alert.WARNING_100) {
            System.out.println("I have received a stock alert from ATM:" +
                    "under 20% of notes of 100 lei left! -> WARNING");
        }
        if (message == Atm.Stock_alert.WARNING_50) {
            System.out.println("I have received a stock alert from ATM:" +
                    "under 15% of notes of 50 lei left! -> WARNING");
        }
        if (message == Atm.Stock_alert.CRITICAL_100) {
            System.out.println("I have received a stock alert from ATM:" +
                    "under 10% of notes of 100 lei left! -> CRITICAL");
        }
        if (message == Atm.Stock_alert.O_STOCK) {
            System.out.println("I have received a stock alert from ATM:" +
                    "close to 0 stock -> REFILL!!");
        }


    }

}
