package com.moderntech.ecommerce.models;

public class Customer {
    private final String id;
    private final String fullName;
    private final String email;

    public Customer(String id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public String id() {
        return id;
    }

    public String fullName() {
        return fullName;
    }

    public String email() {
        return email;
    }
}
