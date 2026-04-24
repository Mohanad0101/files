package com.moderntech.ecommerce.payment;

public final class CashOnDelivery implements PaymentMethod {
    @Override
    public String details() {
        return "CashOnDelivery";
    }
}
