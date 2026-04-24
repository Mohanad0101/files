package part2.part2_2;

public class Fibonacci {
    public static long fibIterative(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be >= 0");
        if (n == 0) return 0;
        if (n == 1) return 1;
        long a = 0, b = 1;
        int i = 2;
        while (i <= n) {
            long next = a + b;
            a = b;
            b = next;
            i++;
        }
        return b;
    }

    public static long fibFor(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be >= 0");
        if (n == 0) return 0;
        if (n == 1) return 1;

        long a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            long next = a + b;
            a = b;
            b = next;
        }
        return b;
    }

    public static long firstGreaterThan(long threshold) {
        if (threshold < 0) return 0;
        long a = 0, b = 1;
        while (a <= threshold) {
            long next = a + b;
            a = b;
            b = next;
        }
        return a;
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 15; i++) {
            System.out.printf("F(%d)=%d | %d%n", i, fibIterative(i), fibFor(i));
        }
        System.out.println("Первое число Фибоначчи > 1000: " + firstGreaterThan(1000));
    }
}
