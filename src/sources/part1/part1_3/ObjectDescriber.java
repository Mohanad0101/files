package part1.part1_3;

import java.util.Arrays;

public class ObjectDescriber {
    public static String describe(Object obj) {
        return switch (obj) {
            case null -> "null: объект отсутствует";
            case Integer i -> i > 0
                ? "Integer: положительное число " + i
                : "Integer: не положительное число " + i;
            case String s when s.isEmpty() -> "String: пустая строка";
            case String s -> "String: непустая строка \"" + s + "\"";
            case Double d -> "Double: " + d;
            case int[] arr -> "int[]: " + Arrays.toString(arr);
            default -> "Object: " + obj + " (" + obj.getClass().getSimpleName() + ")";
        };
    }

    public static void main(String[] args) {
        Object[] samples = {null, 42, -5, "", "Привет", 3.14, new int[]{1, 2, 3}, true};
        for (Object sample : samples) {
            System.out.println(describe(sample));
        }
    }
}
