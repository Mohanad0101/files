package part1.part1_1;

public class NumberClassifier {
    public static String classify(int number) {
        if (number < 0) return "отрицательное";
        if (number == 0) return "ноль";
        if (number <= 9) return "однозначное";
        if (number <= 99) return "двузначное";
        if (number <= 999) return "трёхзначное";
        return "большое";
    }

    public static void main(String[] args) {
        int[] samples = {-5, 0, 7, 42, 100, 1000, -999};
        for (int n : samples) {
            System.out.println(n + " -> " + classify(n));
        }
    }
}
