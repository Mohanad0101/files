package com.moderntech.ecommerce.payment;

import com.moderntech.ecommerce.models.Order;

public class OzonPayment implements Payment {
    @Override
    public PaymentStatus process(Order order, PaymentMethod paymentMethod) {
        System.out.printf(
                "[%s] Processing payment for order %s, amount: %.2f using %s%n",
                providerName(),
                order.orderId(),
                order.totalWithVat(),
                paymentMethod.details()
        );
        return PaymentStatus.SUCCESS;
    }

    @Override
    public String providerName() {
        return "Ozon";
    }
}
