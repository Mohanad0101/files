package task3_2;

public class GradeJournal {

    // Method to calculate average score
    public static double average(int[] grades) {
        if (grades == null || grades.length == 0) return 0;

        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.length;
    }

    // Method to find maximum score
    public static int max(int[] grades) {
        if (grades == null || grades.length == 0) return 0;

        int max = grades[0];
        for (int i = 1; i < grades.length; i++) {
            if (grades[i] > max) {
                max = grades[i];
            }
        }
        return max;
    }

    // Method to find minimum score
    public static int min(int[] grades) {
        if (grades == null || grades.length == 0) return 0;

        int min = grades[0];
        for (int i = 1; i < grades.length; i++) {
            if (grades[i] < min) {
                min = grades[i];
            }
        }
        return min;
    }

    public static void main(String[] args) {
        // Array of student names
        String[] names = {"Алиса", "Борис", "Вера", "Глеб"};

        // Jagged array of grades (each student has different number of grades)
        int[][] grades = {
                {5, 4, 5, 5, 3},        // Алиса: 5 grades
                {3, 3, 4},               // Борис: 3 grades
                {5, 5, 5, 5, 5, 4},      // Вера: 6 grades
                {4, 3, 4, 5}             // Глеб: 4 grades
        };

        System.out.println("=== Журнал оценок ===");

        // Variables to track best student
        String bestStudent = "";
        double bestAverage = -1;

        // Process each student
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            int[] studentGrades = grades[i];

            int numGrades = studentGrades.length;
            double avg = average(studentGrades);
            int maxGrade = max(studentGrades);
            int minGrade = min(studentGrades);

            // Print student info with formatting
            System.out.printf("%-6s | Оценок: %d | Средний: %.2f | Мин: %d | Макс: %d%n",
                    name, numGrades, avg, minGrade, maxGrade);

            // Check if this student has best average
            if (avg > bestAverage) {
                bestAverage = avg;
                bestStudent = name;
            }
        }

        // Print best student
        System.out.println("\nЛучший студент: " + bestStudent +
                " (средний балл: " + String.format("%.2f", bestAverage) + ")");

        // Optional: Additional analysis
        System.out.println("\n--- Дополнительная информация ---");

        // Find class average (average of all grades)
        int totalGrades = 0;
        int totalSum = 0;
        for (int[] studentGrades : grades) {
            for (int grade : studentGrades) {
                totalSum += grade;
                totalGrades++;
            }
        }
        double classAverage = (double) totalSum / totalGrades;
        System.out.printf("Общий средний балл класса: %.2f (всего оценок: %d)%n",
                classAverage, totalGrades);

        // Find highest individual grade
        int highestGrade = 0;
        for (int[] studentGrades : grades) {
            int studentMax = max(studentGrades);
            if (studentMax > highestGrade) {
                highestGrade = studentMax;
            }
        }
        System.out.println("Высшая оценка в классе: " + highestGrade);
    }
}
