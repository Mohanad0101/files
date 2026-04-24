package part2.part2_1;

public class MultiplicationTable {
    public static void printTable() {
        for (int row = 1; row <= 10; row++) {
            System.out.printf("%4d", row);
            for (int col = 1; col <= 10; col++) {
                System.out.printf("%4d", row * col);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        printTable();
    }
}
