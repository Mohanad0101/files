package part1.part1_2;

public class GradeChecker {
    public static String getGradeClassic(int score) {
        if (score < 0 || score > 100) return "Неудовлетворительно (F)";
        int bucket = score / 10;
        switch (bucket) {
            case 10:
            case 9:
                return "Отлично (A)";
            case 8:
                return "Хорошо (B)";
            case 7:
                return "Удовлетворительно (C)";
            case 6:
                return "Слабо (D)";
            default:
                return "Неудовлетворительно (F)";
        }
    }

    public static String getGradeArrow(int score) {
        if (score < 0 || score > 100) return "Неудовлетворительно (F)";
        int bucket = score / 10;
        return switch (bucket) {

            case 10, 9 ->  "Отлично (A)";
            case 8 -> "Хорошо (B)";
            case 7 -> "Удовлетворительно (C)";
            case 6 -> "Слабо (D)";
            default -> "Неудовлетворительно (F)";
        };
    }

    public static void main(String[] args) {
        int[] samples = {95, 85, 73, 62, 45, 100, 0};
        for (int score : samples) {
            System.out.printf("%d : classic: %s | arrow -> %s%n",
                score, getGradeClassic(score), getGradeArrow(score));
        }
    }
}
