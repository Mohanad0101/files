package com.moderntech.ecommerce.models;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private static final double VAT_RATE = 0.15;
    private final List<CartItem> items = new ArrayList<>();

    public void addProduct(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }
        if (quantity > product.stock()) {
            throw new IllegalArgumentException("Requested quantity exceeds available stock.");
        }

        for (int i = 0; i < items.size(); i++) {
            CartItem existing = items.get(i);
            if (existing.product().id().equals(product.id())) {
                int newQuantity = existing.quantity() + quantity;
                if (newQuantity > product.stock()) {
                    throw new IllegalArgumentException("Total quantity exceeds available stock.");
                }
                items.set(i, new CartItem(product, newQuantity));
                return;
            }
        }

        items.add(new CartItem(product, quantity));
    }

    public void removeProduct(String productId) {
        items.removeIf(item -> item.product().id().equals(productId));
    }

    public List<CartItem> getItems() {
        return List.copyOf(items);
    }

    public double subtotal() {
        return items.stream()
                .mapToDouble(CartItem::lineTotal)
                .sum();
    }

    public double totalWithVat() {
        return subtotal() * (1 + VAT_RATE);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }
}
