package Task2_2;

// Sealed interface with permitted records
sealed interface PaymentMethod permits CreditCard, BankTransfer, CryptoWallet {

    // Methods that all payment methods must implement
    String process(double amount);  // returns description of completed operation
    double fee(double amount);      // returns commission
}