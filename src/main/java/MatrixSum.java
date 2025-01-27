import java.util.Random;
import java.util.Scanner;

public class MatrixSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Введите количество строк матрицы: ");
        int rows = scanner.nextInt();

        System.out.print("Введите количество столбцов матрицы: ");
        int cols = scanner.nextInt();

        if (rows < 2 || cols < 2) {
            System.out.println("Размер матрицы должен быть не менее 2x2.");
            return;
        }

        int[][] matrix = new int[rows][cols];
        int borderSum = 0;
        int attempts = 0; 
        final int MAX_ATTEMPTS = 10000000; 

        while (borderSum != 666 && attempts < MAX_ATTEMPTS) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = random.nextInt(100) + 1;
                }
            }

            borderSum = calculateBorderSum(matrix, rows, cols);
            attempts++;
        }

        if (borderSum == 666) {
            System.out.println("Матрица, где сумма граничных элементов равна 666:");
            printMatrix(matrix);
        } else {
            System.out.println("Не удалось найти подходящую матрицу за " + MAX_ATTEMPTS + " попыток.");
        }
    }

    private static int calculateBorderSum(int[][] matrix, int rows, int cols) {
        int sum = 0;

        for (int j = 0; j < cols; j++) {
            sum += matrix[0][j]; 
            sum += matrix[rows - 1][j]; 
        }

        for (int i = 1; i < rows - 1; i++) {
            sum += matrix[i][0]; 
            sum += matrix[i][cols - 1]; 
        }

        return sum;
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
