package part4.part4_1;

public class DiscountCalculator {
    public static double calculateDiscount(double price, String customerType) {
        return switch (customerType.toLowerCase()) {
            case "vip" -> price * 0.30;
            case "regular" -> price * 0.10;
            case "new" -> price * 0.05;
            default -> 0;
        };
    }

    public static double calculateDiscount(double price, int purchaseCount) {
        if (purchaseCount >= 100) return price * 0.20;
        if (purchaseCount >= 50) return price * 0.15;
        if (purchaseCount >= 10) return price * 0.10;
        return 0;
    }

    public static double calculateDiscount(double price, String promoCode, boolean isFirstOrder) {
        double discount = 0;
        if ("SAVE10".equals(promoCode)) discount = price * 0.10;
        if ("SAVE20".equals(promoCode)) discount = price * 0.20;
        if (isFirstOrder) discount += price * 0.05;
        return Math.min(discount, price * 0.50);
    }

    public static double calculateDiscount(double price, int age, String season) {
        double discount = 0;
        if (age >= 60) discount += price * 0.10;
        if ("winter".equalsIgnoreCase(season)) discount += price * 0.08;
        if ("summer".equalsIgnoreCase(season)) discount += price * 0.05;
        return Math.min(discount, price * 0.50);
    }

    public static void main(String[] args) {
        double price = 10000.0;
        System.out.println("VIP: " + calculateDiscount(price, "vip"));
        System.out.println("75 покупок: " + calculateDiscount(price, 75));
        System.out.println("SAVE20 + first: " + calculateDiscount(price, "SAVE20", true));
        System.out.println("Возраст+сезон: " + calculateDiscount(price, 65, "winter"));
    }
}
