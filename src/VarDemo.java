import java.util.ArrayList;
import java.util.List;

public class VarDemo {
    // var field = 10; // Не компилируется — var нельзя использовать для полей класса

    public static void main(String[] args) {

        // Напишите 5 рабочих примеров с var
     //   int, String, ArrayList, array

       var num =Integer.valueOf(5);
        System.out.println("num var:"+num.getClass().getName());
        var text = "Java";
        System.out.println(text + " -> " + text.getClass().getSimpleName());

        var list = new ArrayList<>(List.of("один", "два"));
        System.out.println(list + " -> " + list.getClass().getSimpleName());

        var arr = new int[]{1, 2, 3};
        System.out.println(java.util.Arrays.toString(arr) + " -> " + arr.getClass().getSimpleName());

        var account = new BankAccount("Test", 100);
        System.out.println("BankAccount -> " + account.getClass().getSimpleName());


        // Для каждого выведите тип через getClass().getSimpleName()

        // Затем в комментариях покажите 4 случая, где var не работает

       /* varwithout initialization
        varas method parameter
        varas a class field
          var With null*/

        // 1. Объявление без инициализации
        // var x;
        // x = 5;  // Ошибка: var требует инициализации при объявлении

        // 2. var как параметр метода
        // void method(var param) { }  // Ошибка: var нельзя как тип параметра

        // 3. var для поля класса (уже показано выше)
        // var field = 10;  // Ошибка: var нельзя для поля класса

        // 4. var с null
        // var n = null;  // Ошибка: тип не выводится из null

        // Дополнительные примеры где var не работает:

        // 5. Лямбда-выражения
        // var lambda = (String s) -> s.length();  // Ошибка: нужен явный тип

        // 6. Массивы без new
        // var arr2 = {1, 2, 3};  // Ошибка: нужен new int[]

        // 7. Несколько переменных в одной строке
        // var a = 1, b = 2;  // Ошибка: нельзя объявить несколько var в одной строке

    }
}
