import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Запрос имени файла
        System.out.print("Введите имя файла (например, 1.txt): ");
        String fileName = scanner.nextLine();

        // Запрос операции
        System.out.print("Введите операцию (сложение, вычитание, умножение): ");
        String operation = scanner.nextLine().toLowerCase();

        try {
            // Чтение чисел из файла
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            double number1 = Double.parseDouble(reader.readLine());
            double number2 = Double.parseDouble(reader.readLine());
            reader.close();

            double result = 0;
            switch (operation) {
                case "сложение":
                    result = number1 + number2;
                    break;
                case "вычитание":
                    result = number1 - number2;
                    break;
                case "умножение":
                    result = number1 * number2;
                    break;
                default:
                    System.out.println("Неизвестная операция.");
                    return;
            }

            // Вывод результата на экран
            System.out.println("Результат: " + result);

            // Запись результата в файл
            String outputFileName = fileName.replace(".txt", "_out.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
            writer.write("Результат: " + result);
            writer.close();

            System.out.println("Результат записан в файл: " + outputFileName);
        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлом: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: неверный формат числа в файле.");
        }
    }
}