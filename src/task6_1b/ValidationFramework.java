package task6_1b;

import java.util.List;

// Main class to demonstrate the validation framework
public class ValidationFramework {
    public static void main(String[] args) {
        RegistrationForm valid = new RegistrationForm("Иван", "ivan@mail.ru", 25);
        RegistrationForm invalid = new RegistrationForm("", null, 15);
        RegistrationForm edgeCase = new RegistrationForm("Анна", "anna@mail.ru", 18); // min age
        RegistrationForm edgeCase2 = new RegistrationForm("Петр", "petr@mail.ru", 120); // max age

        System.out.println("=== Валидация корректной формы ===");
        List<String> errors1 = Validator.validate(valid);
        System.out.println(errors1.isEmpty() ? "Все поля валидны!" : errors1);

        System.out.println("\n=== Валидация некорректной формы ===");
        List<String> errors2 = Validator.validate(invalid);
        errors2.forEach(e -> System.out.println("  - " + e));

        System.out.println("\n=== Тест граничных значений ===");
        List<String> errors3 = Validator.validate(edgeCase);
        System.out.println("Возраст 18: " + (errors3.isEmpty() ? "OK" : errors3));

        List<String> errors4 = Validator.validate(edgeCase2);
        System.out.println("Возраст 120: " + (errors4.isEmpty() ? "OK" : errors4));

        // Test with null object
        System.out.println("\n=== Тест с null объектом ===");
        List<String> errors5 = Validator.validate(null);
        System.out.println(errors5);

        // Demonstrate additional validation features
        System.out.println("\n=== Демонстрация всех полей ===");
        RegistrationForm demo = new RegistrationForm("", "", 200);
        System.out.println("Проверка объекта с множеством ошибок:");
        List<String> errors6 = Validator.validate(demo);
        errors6.forEach(e -> System.out.println("  ✗ " + e));

        // Show field values
        System.out.println("\nЗначения полей объекта:");
        System.out.println("  name: \"" + demo.getName() + "\"");
        System.out.println("  email: \"" + demo.getEmail() + "\"");
        System.out.println("  age: " + demo.getAge());
    }
}