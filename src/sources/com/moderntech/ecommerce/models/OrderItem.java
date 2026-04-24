package com.moderntech.ecommerce.models;

public record OrderItem(String productId, String productName, int quantity, double unitPrice) {
    public double lineTotal() {
        return unitPrice * quantity;
    }
}
