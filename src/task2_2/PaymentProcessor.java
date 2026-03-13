package task2_2;

// Class with static method using pattern matching switch
class PaymentProcessor {

    // Static method that uses switch with pattern matching (Java 21+)
    public static void describe(PaymentMethod pm) {
        String description = switch(pm) {
            case CreditCard c -> String.format("Кредитная карта: %s, Владелец: %s",
                    maskCardNumber(c.cardNumber()), c.holder());
            case BankTransfer b -> String.format("Банковский перевод: %s, IBAN: %s",
                    b.bankName(), b.iban());
            case CryptoWallet w -> String.format("Криптокошелек: %s, Валюта: %s",
                    w.address(), w.currency());
        };
        System.out.println("  Детали: " + description);
    }

    // Helper method to mask card number
    private static String maskCardNumber(String cardNumber) {
        if (cardNumber.length() >= 4) {
            return "*" + cardNumber.substring(cardNumber.length() - 4);
        }
        return cardNumber;
    }
}
