package Task2_2;

// Demo class to test the payment system
public class PaymentDemo {
    public static void main(String[] args) {
        // Array of different payment methods
        PaymentMethod[] payments = {
                new CreditCard("4111222233334444", "Иван Петров"),
                new BankTransfer("Сбербанк", "RU1234567890"),
                new CryptoWallet("0xABC123", "BTC")
        };

        double amount = 10000;

        // Process each payment method
        for (PaymentMethod pm : payments) {
            System.out.println(pm.process(amount));
            System.out.printf("  Комиссия: %.2f руб.%n", pm.fee(amount));
            PaymentProcessor.describe(pm);
            System.out.println();
        }
    }
}