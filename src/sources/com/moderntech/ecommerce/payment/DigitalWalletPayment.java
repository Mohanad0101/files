package com.moderntech.ecommerce.payment;

public final class DigitalWalletPayment implements PaymentMethod {
    private final String walletId;

    public DigitalWalletPayment(String walletId) {
        this.walletId = walletId;
    }

    @Override
    public String details() {
        return "DigitalWallet{id='%s'}".formatted(walletId);
    }
}
