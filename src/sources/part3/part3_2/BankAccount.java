package part3.part3_2;

public class BankAccount {
    private final String accountNumber;
    private double balance;
    private final String owner;
    private int failedAttempts;
    private boolean blocked;
    private String pin;

    public BankAccount(String accountNumber, String owner, String pin, double initialBalance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.pin = pin;
        this.balance = initialBalance;
    }

    public boolean validatePin(String enteredPin) {
        return pin != null && pin.equals(enteredPin);
    }

    public boolean deposit(double amount) {
        if (amount <= 0) return false;
        balance += amount;
        return true;
    }

    public boolean withdraw(String enteredPin, double amount) {
        if (blocked) return false;

        if (!validatePin(enteredPin)) {
            failedAttempts++;
            if (failedAttempts >= 3) blocked = true;
            return false;
        }

        failedAttempts = 0;
        if (amount <= 0 || amount > balance) return false;
        balance -= amount;
        return true;
    }

    public String getMaskedBalance() {
        if (balance > 100_000) return "*****";
        return String.format("%.2f", balance);
    }

    @Override
    public String toString() {
        return String.format("BankAccount{%s, owner=%s, balance=%s}%s",
            accountNumber, owner, getMaskedBalance(), blocked ? " [ЗАБЛОКИРОВАН]" : "");
    }
}
