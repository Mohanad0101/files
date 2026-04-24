package com.moderntech.ecommerce.payment;

import com.moderntech.ecommerce.models.Order;

public interface Payment {
    PaymentStatus process(Order order, PaymentMethod paymentMethod);

    String providerName();
}
