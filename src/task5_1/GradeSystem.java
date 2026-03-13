package task5_1;

import java.util.*;

public class GradeSystem {

    // Enum Grade with description, GPA value, and utility methods
    public enum Grade {
        A("Excellent", 4.0),
        B("Good", 3.0),
        C("Satisfactory", 2.0),
        D("Unsatisfactory", 1.0),
        F("Failure", 0.0);

        private final String description;
        private final double gpaValue;

        // Constructor
        Grade(String description, double gpaValue) {
            this.description = description;
            this.gpaValue = gpaValue;
        }

        // Getters
        public String getDescription() {
            return description;
        }

        public double getGpaValue() {
            return gpaValue;
        }

        // Method to check if grade is passing (not F and not D)
        public boolean isPassing() {
            return this != F && this != D;
        }

        // Static method to convert numerical score to Grade
        public static Grade fromScore(int score) {
            if (score >= 90) return A;
            if (score >= 80) return B;
            if (score >= 70) return C;
            if (score >= 60) return D;
            return F;
        }
    }

    // Record Student with compact constructor validation
    public record Student(String name, int id) {

        // Compact constructor with validation
        public Student {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Name cannot be null or empty");
            }
            if (id <= 0) {
                throw new IllegalArgumentException("ID must be positive");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Система оценивания студентов ===\n");

        // Create students
        List<Student> students = Arrays.asList(
                new Student("Анна", 101),
                new Student("Борис", 102),
                new Student("Виктория", 103),
                new Student("Григорий", 104),
                new Student("Дарья", 105),
                new Student("Евгений", 106),
                new Student("Елена", 107)
        );

        // Assign numerical scores for each student
        int[] scores = {95, 82, 67, 73, 58, 91, 45};

        // Create EnumMap to group students by grade
        EnumMap<Grade, List<Student>> gradeMap = new EnumMap<>(Grade.class);

        // Initialize EnumMap with empty lists for each grade
        for (Grade grade : Grade.values()) {
            gradeMap.put(grade, new ArrayList<>());
        }

        // Group students by their grade
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            int score = scores[i];
            Grade grade = Grade.fromScore(score);

            gradeMap.get(grade).add(student);

            System.out.printf("%s (ID: %d) - оценка: %s (%s), балл: %d%n",
                    student.name(), student.id(), grade, grade.getDescription(), score);
        }

        // Create EnumSet for passing grades (using isPassing() method)
        EnumSet<Grade> passingGrades = EnumSet.allOf(Grade.class);
        passingGrades.removeIf(grade -> !grade.isPassing());

        System.out.println("\n--- Сводка по оценкам ---");

        // Output summary for each grade
        double totalGpa = 0;
        int totalStudents = 0;

        for (Grade grade : Grade.values()) {
            List<Student> studentsWithGrade = gradeMap.get(grade);
            int count = studentsWithGrade.size();

            if (count > 0) {
                System.out.printf("\n%s (%s, GPA: %.1f) - %d студент(ов):%n",
                        grade, grade.getDescription(), grade.getGpaValue(), count);

                for (Student student : studentsWithGrade) {
                    System.out.printf("  - %s (ID: %d)%n", student.name(), student.id());
                }

                totalGpa += grade.getGpaValue() * count;
                totalStudents += count;
            } else {
                System.out.printf("\n%s (%s) - нет студентов%n", grade, grade.getDescription());
            }
        }

        // Calculate and display average GPA
        double averageGpa = totalStudents > 0 ? totalGpa / totalStudents : 0;
        System.out.printf("\n--- Статистика ---");
        System.out.printf("%nВсего студентов: %d", totalStudents);
        System.out.printf("%nСредний GPA: %.2f", averageGpa);

        // Display passing grades using EnumSet
        System.out.printf("%n%nПроходные оценки (%s): ", passingGrades);
        for (Grade grade : passingGrades) {
            System.out.print(grade + " ");
        }

        // Count students with passing grades
        int passingCount = 0;
        for (Grade grade : passingGrades) {
            passingCount += gradeMap.get(grade).size();
        }
        System.out.printf("%nСтудентов с проходными оценками: %d из %d",
                passingCount, totalStudents);

        // Additional: Find highest and lowest grades
        Grade highestGrade = null;
        Grade lowestGrade = null;

        for (Grade grade : Grade.values()) {
            if (!gradeMap.get(grade).isEmpty()) {
                if (highestGrade == null) highestGrade = grade;
                lowestGrade = grade;
            }
        }

        if (highestGrade != null && lowestGrade != null) {
            System.out.printf("%nВысшая оценка: %s (%s)",
                    highestGrade, highestGrade.getDescription());
            System.out.printf("%nНизшая оценка: %s (%s)",
                    lowestGrade, lowestGrade.getDescription());
        }

        // Demonstrate validation
        System.out.println("\n\n--- Проверка валидации ---");
        try {
            Student invalid1 = new Student("", 201);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при создании студента с пустым именем: " + e.getMessage());
        }

        try {
            Student invalid2 = new Student("Петр", -5);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при создании студента с отрицательным ID: " + e.getMessage());
        }
    }
}