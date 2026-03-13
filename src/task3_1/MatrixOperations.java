package task3_1;

public class MatrixOperations {

    // Method to print matrix in formatted form (each number 4 characters wide)
    public static void print(int[][] matrix) {
        if (matrix == null) {
            System.out.println("null");
            return;
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    // Method to transpose matrix (rows become columns)
    public static int[][] transpose(int[][] matrix) {
        if (matrix == null) return null;

        int rows = matrix.length;
        int cols = matrix[0].length;

        // Create transposed matrix with swapped dimensions
        int[][] result = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }

        return result;
    }

    // Method to multiply two matrices
    public static int[][] multiply(int[][] a, int[][] b) {
        // Check for null inputs
        if (a == null || b == null) {
            System.err.println("Ошибка: одна из матриц равна null");
            return null;
        }

        // Check if matrices are empty
        if (a.length == 0 || b.length == 0) {
            System.err.println("Ошибка: матрица пуста");
            return null;
        }

        int aRows = a.length;
        int aCols = a[0].length;
        int bRows = b.length;
        int bCols = b[0].length;

        // Check if multiplication is possible (aCols must equal bRows)
        if (aCols != bRows) {
            System.err.println("Ошибка: несовместимые размеры матриц для умножения");
            System.err.printf("A: %dx%d, B: %dx%d%n", aRows, aCols, bRows, bCols);
            return null;
        }

        // Create result matrix with dimensions aRows x bCols
        int[][] result = new int[aRows][bCols];

        // Perform multiplication: C[i][j] = sum(A[i][k] * B[k][j]) for all k
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bCols; j++) {
                for (int k = 0; k < aCols; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return result;
    }

    // Method to calculate sum of main diagonal elements
    public static int diagonalSum(int[][] matrix) {
        if (matrix == null) {
            System.err.println("Ошибка: матрица равна null");
            return 0;
        }

        if (matrix.length == 0) return 0;

        int sum = 0;
        int minDimension = Math.min(matrix.length, matrix[0].length);

        for (int i = 0; i < minDimension; i++) {
            sum += matrix[i][i];
        }

        return sum;
    }

    public static void main(String[] args) {
        int[][] a = {
                {1, 2, 3},
                {4, 5, 6}
        };

        int[][] b = {
                {7,  8},
                {9,  10},
                {11, 12}
        };

        System.out.println("Матрица A (2x3):");
        print(a);

        System.out.println("\nТранспонированная A (3x2):");
        print(transpose(a));

        System.out.println("\nМатрица B (3x2):");
        print(b);

        int[][] c = multiply(a, b);
        System.out.println("\nA * B (2x2):");
        print(c);

        System.out.println("\nСумма диагонали A*B: " + diagonalSum(c));

        // Additional test: incompatible matrices
        System.out.println("\n--- Тест с несовместимыми матрицами ---");
        int[][] d = {{1, 2}, {3, 4}}; // 2x2
        int[][] e = {{5, 6, 7}, {8, 9, 10}}; // 2x3
        System.out.println("Попытка умножить 2x2 на 2x3:");
        multiply(d, e);
    }
}
