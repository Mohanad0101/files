package com.moderntech.ecommerce.main;

import com.moderntech.ecommerce.enums.OrderStatus;
import com.moderntech.ecommerce.enums.ProductCategory;
import com.moderntech.ecommerce.models.Customer;
import com.moderntech.ecommerce.models.Order;
import com.moderntech.ecommerce.models.OrderItem;
import com.moderntech.ecommerce.models.Product;
import com.moderntech.ecommerce.models.ShoppingCart;
import com.moderntech.ecommerce.payment.CashOnDelivery;
import com.moderntech.ecommerce.payment.CreditCardPayment;
import com.moderntech.ecommerce.payment.DigitalWalletPayment;
import com.moderntech.ecommerce.payment.OzonPayment;
import com.moderntech.ecommerce.payment.Payment;
import com.moderntech.ecommerce.payment.PaymentMethod;
import com.moderntech.ecommerce.payment.PaymentStatus;
import com.moderntech.ecommerce.payment.WildberriesPayment;

import java.util.HashMap;
import java.util.Map;

public class ECommerceApp {
    public static void main(String[] args) {
        HashMap<String, Product> catalog = buildCatalog();
        printCatalog(catalog);

        Customer customer = new Customer("C-1001", "Mohanad User", "mohanad@example.com");
        System.out.printf("Customer created: %s (%s)%n%n", customer.fullName(), customer.email());

        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(catalog.get("P100"), 1);
        cart.addProduct(catalog.get("P200"), 1);
        cart.addProduct(catalog.get("P500"), 2);
        printCart(cart);

        // Checkout converts cart lines into immutable order lines.
        Order order = Order.fromCart(customer, cart);
        cart.clear();
        printOrderDetails(order);

        order.updateStatus(OrderStatus.CONFIRMED);
        System.out.println("Order status changed to: " + order.status());
        order.updateStatus(OrderStatus.PROCESSING);
        System.out.println("Order status changed to: " + order.status());
        System.out.println();

        runPaymentScenario(order, new OzonPayment(), new CreditCardPayment("Mohanad User", "**** **** **** 4242"));
        runPaymentScenario(order, new WildberriesPayment(), new DigitalWalletPayment("wallet_7788"));
        runPaymentScenario(order, new OzonPayment(), new CashOnDelivery());

        System.out.println("\n=== Final Order Summary ===");
        System.out.printf("Order ID: %s%n", order.orderId());
        System.out.printf("Customer: %s%n", order.customer().fullName());
        System.out.printf("Lines: %d%n", order.orderItems().size());
        System.out.printf("Total with VAT: %.2f%n", order.totalWithVat());
        System.out.printf("Current status: %s%n", order.status());
    }

    private static HashMap<String, Product> buildCatalog() {
        HashMap<String, Product> catalog = new HashMap<>();
        catalog.put("P100", new Product("P100", "Pixel Phone 9", ProductCategory.SMARTPHONE, 799.00, 20));
        catalog.put("P200", new Product("P200", "ThinkPad X1", ProductCategory.LAPTOP, 1450.00, 8));
        catalog.put("P300", new Product("P300", "Galaxy Tab S", ProductCategory.TABLET, 620.00, 12));
        catalog.put("P400", new Product("P400", "Mirrorless Cam Z", ProductCategory.CAMERA, 980.00, 5));
        catalog.put("P500", new Product("P500", "USB-C Charger", ProductCategory.ACCESSORY, 35.00, 50));
        return catalog;
    }

    private static void printCatalog(Map<String, Product> catalog) {
        System.out.println("=== Product Catalog ===");
        catalog.values().forEach(product -> System.out.printf(
                "%s | %s | category=%s | price=%.2f | stock=%d%n",
                product.id(),
                product.name(),
                product.category(),
                product.price(),
                product.stock()
        ));
        System.out.println();
    }

    private static void printCart(ShoppingCart cart) {
        System.out.println("=== Shopping Cart ===");
        cart.getItems().forEach(item -> System.out.printf(
                "%s x%d => %.2f%n",
                item.product().name(),
                item.quantity(),
                item.lineTotal()
        ));
        System.out.printf("Subtotal: %.2f%n", cart.subtotal());
        System.out.printf("Total with VAT: %.2f%n%n", cart.totalWithVat());
    }

    private static void printOrderDetails(Order order) {
        System.out.println("=== Checkout Order ===");
        System.out.printf("Order ID: %s%n", order.orderId());
        for (OrderItem item : order.orderItems()) {
            System.out.printf("%s x%d => %.2f%n", item.productName(), item.quantity(), item.lineTotal());
        }
        System.out.printf("Order subtotal: %.2f%n", order.subtotal());
        System.out.printf("Order total with VAT: %.2f%n", order.totalWithVat());
        System.out.printf("Order status: %s%n%n", order.status());
    }

    private static void runPaymentScenario(Order order, Payment paymentProvider, PaymentMethod paymentMethod) {
        PaymentStatus status = paymentProvider.process(order, paymentMethod);
        System.out.printf("Result: %s payment status = %s%n%n", paymentProvider.providerName(), status);
    }
}
