// 19. Создать класс Matrix для работы с двумерными матрицами.
// Создать методы для генерации нулевой матрицы, а также для генерации матрицы со случайными величинами –
// применить Math.random(). Реализовать метод сложения матриц.

import java.util.Random;

public class Matrix {
    public int rows;
    public int cols;
    public int[][] num;

    // Конструктор класса
    public Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.num = new int[rows][cols];
    }

    public static Matrix zeroMatrix(int rows, int cols){
        return new Matrix(rows, cols);
    }

    public static Matrix randomMatrix(int rows, int cols){
        Matrix matrix = new Matrix(rows, cols);
        Random random = new Random();
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                matrix.num[i][j] = random.nextInt(100);
            }
        }
        return matrix;
    }

    public Matrix sumMatrix(Matrix matrices){
        if (this.rows == matrices.rows && this.cols == matrices.cols){
            Matrix summa = new Matrix(this.rows, this.cols);

            for (int i = 0; i < rows; i++){
                for (int j = 0; j < cols; j++){
                    summa.num[i][j] = this.num[i][j] + matrices.num[i][j];
                }
            }
            return summa;
        } else {
            System.out.println("У матриц неодинаковый размер.");
            return null;
        }
    }

    public void printMatrix(){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                System.out.print(num[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("Нулевая матрица: ");
        Matrix zero = zeroMatrix(3, 3);
        zero.printMatrix();

        System.out.println();

        System.out.println("Матрица из случайных чисел: ");
        Matrix randomMatr = randomMatrix(3, 3);
        randomMatr.printMatrix();

        System.out.println();

        System.out.println("Сумма матриц: ");
        Matrix matrix1 = randomMatrix(3, 3);
        Matrix matrix2 = randomMatrix(3, 3);
        Matrix total = matrix1.sumMatrix(matrix2);
        total.printMatrix();
    }

}
