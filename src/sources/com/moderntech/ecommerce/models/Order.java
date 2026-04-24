package com.moderntech.ecommerce.models;

import com.moderntech.ecommerce.enums.OrderStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private final String orderId;
    private final Customer customer;
    private final List<OrderItem> orderItems;
    private final double subtotal;
    private final double totalWithVat;
    private OrderStatus status;

    public Order(
            String orderId,
            Customer customer,
            List<OrderItem> orderItems,
            double subtotal,
            double totalWithVat,
            OrderStatus status
    ) {
        this.orderId = orderId;
        this.customer = customer;
        this.orderItems = new ArrayList<>(orderItems);
        this.subtotal = subtotal;
        this.totalWithVat = totalWithVat;
        this.status = status;
    }

    public static Order fromCart(Customer customer, ShoppingCart cart) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cannot create order from empty cart.");
        }

        List<OrderItem> items = cart.getItems().stream()
                .map(item -> new OrderItem(
                        item.product().id(),
                        item.product().name(),
                        item.quantity(),
                        item.product().price()
                ))
                .toList();

        return new Order(
                UUID.randomUUID().toString(),
                customer,
                items,
                cart.subtotal(),
                cart.totalWithVat(),
                OrderStatus.PENDING
        );
    }

    public void updateStatus(OrderStatus newStatus) {
        this.status = newStatus;
    }

    public String orderId() {
        return orderId;
    }

    public Customer customer() {
        return customer;
    }

    public List<OrderItem> orderItems() {

        return List.copyOf(orderItems);
    }

    public double subtotal() {
        return subtotal;
    }

    public double totalWithVat() {
        return totalWithVat;
    }

    public OrderStatus status() {

        return status;
    }
}
