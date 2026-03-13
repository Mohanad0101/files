package Task2_2;

// Record for CryptoWallet payments
record CryptoWallet(String address, String currency) implements PaymentMethod {

    @Override
    public String process(double amount) {
        return String.format("Криптоплатёж (%s): %.0f руб.", currency, amount);
    }

    @Override
    public double fee(double amount) {
        // Commission = 1.5% of amount
        return amount * 0.015;
    }
}