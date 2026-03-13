package task7_2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrderAnalytics {

    // Record Order with total() method
    record Order(String customer, String product, double price, int quantity, String category) {
        double total() {
            return price * quantity;
        }

        // For better debugging
        @Override
        public String toString() {
            return String.format("%s: %s x%d = %.2f (%s)",
                    customer, product, quantity, total(), category);
        }
    }

    public static void main(String[] args) {
        // Create list of orders (10+ orders)
        List<Order> orders = Arrays.asList(
                new Order("Иван", "Ноутбук", 75000, 1, "Электроника"),
                new Order("Мария", "Смартфон", 45000, 2, "Электроника"),
                new Order("Петр", "Книга", 800, 3, "Книги"),
                new Order("Анна", "Планшет", 35000, 1, "Электроника"),
                new Order("Иван", "Наушники", 5000, 2, "Электроника"),
                new Order("Елена", "Кофеварка", 12000, 1, "Бытовая техника"),
                new Order("Сергей", "Куртка", 8500, 1, "Одежда"),
                new Order("Мария", "Сумка", 3500, 2, "Аксессуары"),
                new Order("Петр", "Монитор", 28000, 1, "Электроника"),
                new Order("Анна", "Джинсы", 4500, 2, "Одежда"),
                new Order("Иван", "Мышь", 1500, 3, "Электроника"),
                new Order("Елена", "Чайник", 3000, 1, "Бытовая техника")
        );

        System.out.println("=== Все заказы ===");
        orders.forEach(System.out::println);
        System.out.println();

        // 1. Find all orders over 5000 rubles (by total()) and display them
        System.out.println("=== Заказы дороже 5000 руб. ===");
        orders.stream()
                .filter(order -> order.total() > 5000)
                .forEach(System.out::println);
        System.out.println();

        // 2. Get a list of unique customer names, sorted alphabetically
        System.out.println("=== Уникальные покупатели (по алфавиту) ===");
        List<String> uniqueCustomers = orders.stream()
                .map(Order::customer)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        uniqueCustomers.forEach(System.out::println);
        System.out.println();

        // 3. Calculate the total revenue (sum of all total())
        System.out.println("=== Общая выручка ===");
        double totalRevenue = orders.stream()
                .mapToDouble(Order::total)
                .sum();
        System.out.printf("Всего: %.2f руб.%n%n", totalRevenue);

        // 4. Find the most expensive order (by total())
        System.out.println("=== Самый дорогой заказ ===");
        Optional<Order> mostExpensive = orders.stream()
                .max(Comparator.comparingDouble(Order::total));
        mostExpensive.ifPresentOrElse(
                order -> System.out.println(order + "\n"),
                () -> System.out.println("Нет заказов\n")
        );

        // 5. Count the number of orders in each category
        System.out.println("=== Количество заказов по категориям ===");
        Map<String, Long> ordersByCategory = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::category,
                        Collectors.counting()
                ));
        ordersByCategory.forEach((category, count) ->
                System.out.printf("%s: %d заказ(ов)%n", category, count));
        System.out.println();

        // 6. Calculate the average order value for each customer
        System.out.println("=== Средняя сумма заказа по покупателям ===");
        Map<String, Double> avgByCustomer = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::customer,
                        Collectors.averagingDouble(Order::total)
                ));
        avgByCustomer.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> System.out.printf("%s: %.2f руб.%n", e.getKey(), e.getValue()));
        System.out.println();

        // 7. Divide orders into two groups: expensive (total > 3000) and cheap
        System.out.println("=== Разделение на дорогие и дешевые заказы ===");
        Map<Boolean, List<Order>> partitionedOrders = orders.stream()
                .collect(Collectors.partitioningBy(
                        order -> order.total() > 3000
                ));

        System.out.println("Дорогие заказы (>3000 руб.):");
        partitionedOrders.get(true).forEach(order ->
                System.out.printf("  %s: %.2f руб.%n", order.product(), order.total()));

        System.out.println("\nДешевые заказы (≤3000 руб.):");
        partitionedOrders.get(false).forEach(order ->
                System.out.printf("  %s: %.2f руб.%n", order.product(), order.total()));
        System.out.println();

        // 8. Get a list of product names over 1000 rubles, without duplicates, in uppercase
        System.out.println("=== Уникальные товары дороже 1000 руб. (заглавными) ===");
        List<String> expensiveProducts = orders.stream()
                .filter(order -> order.price() > 1000)
                .map(Order::product)
                .map(String::toUpperCase)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        expensiveProducts.forEach(System.out::println);
        System.out.println();

        // Bonus: Additional analytics
        System.out.println("=== Дополнительная аналитика ===");

        // Top 3 customers by total spending
        System.out.println("Топ-3 покупателя по общей сумме:");
        orders.stream()
                .collect(Collectors.groupingBy(
                        Order::customer,
                        Collectors.summingDouble(Order::total)
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .forEach(e -> System.out.printf("  %s: %.2f руб.%n", e.getKey(), e.getValue()));

        // Most popular category by order count
        System.out.println("\nСамая популярная категория:");
        ordersByCategory.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(e -> System.out.printf("  %s: %d заказов%n", e.getKey(), e.getValue()));

        // Average quantity per order
        System.out.println("\nСреднее количество товаров в заказе:");
        double avgQuantity = orders.stream()
                .mapToInt(Order::quantity)
                .average()
                .orElse(0);
        System.out.printf("  %.2f шт.%n", avgQuantity);
    }
}