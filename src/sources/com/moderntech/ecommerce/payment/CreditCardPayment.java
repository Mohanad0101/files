package com.moderntech.ecommerce.payment;

public final class CreditCardPayment implements PaymentMethod {
    private final String cardHolder;
    private final String maskedCardNumber;

    public CreditCardPayment(String cardHolder, String maskedCardNumber) {
        this.cardHolder = cardHolder;
        this.maskedCardNumber = maskedCardNumber;
    }

    @Override
    public String details() {
        return "CreditCard{holder='%s', card='%s'}".formatted(cardHolder, maskedCardNumber);
    }
}
