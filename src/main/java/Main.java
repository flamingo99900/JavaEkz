//Напишите программу, которая получает в качестве входных данных два числа. 
//Эти числа являются количество строк и столбцов двумерной коллекции целых чисел. 
//Далее элементы заполняются случайными числами и выводятся в консоль в виде таблицы.

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Получение количества строк и столбцов от пользователя
        System.out.print("Введите количество строк: ");
        int rows = scanner.nextInt();

        System.out.print("Введите количество столбцов: ");
        int cols = scanner.nextInt();

        // Проверка на корректность ввода
        if (rows <= 0 || cols <= 0) {
            System.out.println("Количество строк и столбцов должно быть больше нуля.");
            return;
        }

        // Создание и заполнение массива случайными числами
        int[][] matrix = new int[rows][cols];
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(100); // Случайные числа от 0 до 99
            }
        }

        // Вывод массива в виде таблицы
        System.out.println("Сгенерированная таблица:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("%4d", matrix[i][j]); // Выравнивание ширины для красоты
            }
            System.out.println();
        }

        scanner.close();
    }
}
