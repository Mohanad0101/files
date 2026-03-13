package task2_2;

// Record for BankTransfer payments
record BankTransfer(String bankName, String iban) implements PaymentMethod {

    @Override
    public String process(double amount) {
        return String.format("Перевод через %s: %.0f руб.", bankName, amount);
    }

    @Override
    public double fee(double amount) {
        // Commission = fixed 50 rubles
        return 50.0;
    }
}