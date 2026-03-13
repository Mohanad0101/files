package task7_1;

import java.util.*;
import java.util.function.*;

public class RefactorStep2 {
    public static void main(String[] args) {
        List<String> cities = Arrays.asList("Москва", "Берлин", "Токио", "Нью-Йорк", "Париж");

        // 1. Сортировка по длине - CANNOT be method reference (needs parameters)
        cities.sort((a, b) -> Integer.compare(a.length(), b.length()));

        // 2. Вывод каждого элемента - CAN be method reference
        cities.forEach(System.out::println);

        // 3. Преобразование в верхний регистр - CAN be method reference (instance method)
        Function<String, String> toUpper = String::toUpperCase;

        // 4. Проверка длины > 5 - CANNOT be method reference (has logic)
        Predicate<String> isLong = s -> s.length() > 5;

        // 5. Формирование строки с восклицательным знаком - CANNOT be method reference (string concatenation)
        Function<String, String> exclaim = s -> s + "!";

        // 6. Создание нового списка - CAN be method reference (constructor)
        Supplier<List<String>> listFactory = ArrayList::new;

        // Использование
        List<String> result = listFactory.get();
        for (String city : cities) {
            if (isLong.test(city)) {
                result.add(toUpper.apply(city));
            }
        }
        System.out.println("Длинные города: " + result);

        // Дополнительное использование exclaim
        System.out.println("\nГорода с восклицанием:");
        cities.stream()
                .map(exclaim)
                .forEach(System.out::println);
    }
    /*

    #	Original	Lambda	Method                   Reference	              Can be Method Ref?
1	Comparator	(a,b) -> Integer.compare(a.length(),  b.length())	-     	 No - needs both parameters
2	Consumer	city -> System.out.println(city)	 System.out::println	       Yes
3	Function	s -> s.toUpperCase()	             String::toUpperCase	                   Yes
4	Predicate	s -> s.length() > 5	-	                              No - contains comparison
5	Function	s -> s + "!"	-	                               No - contains concatenation
6	Supplier	() -> new ArrayList<>()
     */
}