package task2_1;

public class EmployeeBonus {
    public static void main(String[] args) {
        Employee[] team = {
                new Manager("Ольга", 120000.0, 5),
                new Developer("Андрей", 95000.0, "Java"),
                new Developer("Мария", 100000.0, "Python"),
                new Intern("Стажёр Петя", 30000.0)
        };

        System.out.println("=== Расчёт бонусов ===");
        double totalBudget = 0;
        for (Employee e : team) {
            System.out.println(e);
            totalBudget += e.totalCompensation();
        }
        System.out.printf("\nОбщий бюджет: %.0f руб.%n", totalBudget);
    }
}
