package task4_2;

public class StringPoolLab {
    public static void main(String[] args) {
        System.out.println("=== String Pool Research ===\n");

        // Creating strings in different ways
        String s1 = "Hello";                    // literal - goes to string pool
        String s2 = "Hello";                    // another literal - same pool reference
        String s3 = new String("Hello");    // new object - heap, not pool
        String s4 = new String("Hello");    // another new object - different heap reference
        String s5 = s3.intern();                 // returns pool reference (same as s1)
        String s6 = "Hel" + "lo";                // compile-time constant -> pool
        String half = "Hel";
        String s7 = half + "lo";                  // runtime concatenation -> new heap object

        System.out.println("Созданные строки:");
        System.out.println("s1 = \"Hello\" (literal)");
        System.out.println("s2 = \"Hello\" (literal)");
        System.out.println("s3 = new String(\"Hello\")");
        System.out.println("s4 = new String(\"Hello\")");
        System.out.println("s5 = s3.intern()");
        System.out.println("s6 = \"Hel\" + \"lo\" (compile-time concat)");
        System.out.println("s7 = half + \"lo\" (runtime concat, half = \"Hel\")\n");

        // 1. s1 vs s2 - both literals
        System.out.println("1. s1 vs s2:");
        System.out.println("   Предсказание: == true, .equals() true");
        System.out.println("   Объяснение: Оба литерала - компилятор помещает их в пул строк,");
        System.out.println("               ссылаются на один и тот же объект в пуле");
        System.out.println("   Результат: == " + (s1 == s2) + ", .equals() " + s1.equals(s2) + "\n");

        // 2. s1 vs s3 - literal vs new String
        System.out.println("2. s1 vs s3:");
        System.out.println("   Предсказание: == false, .equals() true");
        System.out.println("   Объяснение: s1 в пуле, s3 новый объект в куче - разные ссылки,");
        System.out.println("               но содержимое одинаковое");
        System.out.println("   Результат: == " + (s1 == s3) + ", .equals() " + s1.equals(s3) + "\n");

        // 3. s3 vs s4 - two new String objects
        System.out.println("3. s3 vs s4:");
        System.out.println("   Предсказание: == false, .equals() true");
        System.out.println("   Объяснение: Два разных объекта в куче, созданные через new,");
        System.out.println("               разные ссылки, одинаковое содержимое");
        System.out.println("   Результат: == " + (s3 == s4) + ", .equals() " + s3.equals(s4) + "\n");

        // 4. s1 vs s5 - literal vs interned
        System.out.println("4. s1 vs s5:");
        System.out.println("   Предсказание: == true, .equals() true");
        System.out.println("   Объяснение: intern() возвращает ссылку на объект из пула,");
        System.out.println("               s5 теперь ссылается на тот же объект что и s1");
        System.out.println("   Результат: == " + (s1 == s5) + ", .equals() " + s1.equals(s5) + "\n");

        // 5. s1 vs s6 - literal vs compile-time concatenation
        System.out.println("5. s1 vs s6:");
        System.out.println("   Предсказание: == true, .equals() true");
        System.out.println("   Объяснение: Конкатенация литералов выполняется компилятором,");
        System.out.println("               s6 превращается в литерал \"Hello\" и попадает в пул");
        System.out.println("   Результат: == " + (s1 == s6) + ", .equals() " + s1.equals(s6) + "\n");

        // 6. s1 vs s7 - literal vs runtime concatenation
        System.out.println("6. s1 vs s7:");
        System.out.println("   Предсказание: == false, .equals() true");
        System.out.println("   Объяснение: Конкатенация с переменной происходит во время выполнения,");
        System.out.println("               создается новый объект StringBuilder и новый String в куче");
        System.out.println("   Результат: == " + (s1 == s7) + ", .equals() " + s1.equals(s7) + "\n");

        // Bonus: StringBuilder comparison
        System.out.println("--- StringBuilder experiment ---");
        StringBuilder sb = new StringBuilder();
        sb.append('H').append('e').append('l').append('l').append('o');
        String s8 = sb.toString();  // new heap object

        System.out.println("s8 = StringBuilder \"Hello\" (built letter by letter)");
        System.out.println("s8 vs s1:");
        System.out.println("   Предсказание: == false, .equals() true");
        System.out.println("   Объяснение: StringBuilder создает новый объект в куче,");
        System.out.println("               не связанный с пулом строк");
        System.out.println("   Результат: == " + (s1 == s8) + ", .equals() " + s1.equals(s8));

        // Summary diagram
        System.out.println("\n=== String Pool Diagram ===");
        System.out.println("        Пул строк (String Pool)");
        System.out.println("        ┌─────────────────┐");
        System.out.println("s1 ────▶│                 │");
        System.out.println("s2 ────▶│    \"Hello\"      │");
        System.out.println("s5 ────▶│                 │");
        System.out.println("s6 ────▶│                 │");
        System.out.println("        └─────────────────┘");
        System.out.println("              ▲");
        System.out.println("              │ intern()");
        System.out.println("        ┌─────┴──────┐");
        System.out.println("        │    Куча    │");
        System.out.println("        │  (Heap)    │");
        System.out.println("s3 ────▶│ \"Hello\"   │");
        System.out.println("s4 ────▶│ \"Hello\"   │");
        System.out.println("s7 ────▶│ \"Hello\"   │");
        System.out.println("s8 ────▶│ \"Hello\"   │");
        System.out.println("        └────────────┘");
    }
}