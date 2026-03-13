package Task2_2;

// Record for CreditCard payments
record CreditCard(String cardNumber, String holder) implements PaymentMethod {

    @Override
    public String process(double amount) {
        // Get last 4 digits of card number
        String last4 = cardNumber.length() >= 4 ?
                cardNumber.substring(cardNumber.length() - 4) : cardNumber;
        return String.format("Оплата картой *%s: %.0f руб.", last4, amount);
    }

    @Override
    public double fee(double amount) {
        // Commission = 2% of amount
        return amount * 0.02;
    }
}
