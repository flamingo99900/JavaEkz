import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (FileWriter writer = new FileWriter("results.txt", true)) {
            System.out.println("Выберите операцию: сложение, вычитание, умножение, деление, возведение, логарифм");
            String operation = scanner.next().toLowerCase();

            double result = 0;
            if (operation.equals("логарифм")) {
                System.out.println("Введите основание логарифма и число:");
                double base = scanner.nextDouble();
                double value = scanner.nextDouble();
                if (base <= 0 || base == 1 || value <= 0) {
                    throw new IllegalArgumentException("Некорректные значения для логарифма.");
                }
                result = Math.log(value) / Math.log(base);
            } else {
                System.out.println("Введите два числа:");
                double num1 = scanner.nextDouble();
                double num2 = scanner.nextDouble();

                switch (operation) {
                    case "сложение":
                        result = num1 + num2;
                        break;
                    case "вычитание":
                        result = num1 - num2;
                        break;
                    case "умножение":
                        result = num1 * num2;
                        break;
                    case "деление":
                        if (num2 == 0) {
                            throw new ArithmeticException("Деление на ноль запрещено.");
                        }
                        result = num1 / num2;
                        break;
                    case "возведение":
                        result = Math.pow(num1, num2);
                        break;
                    default:
                        throw new IllegalArgumentException("Неизвестная операция.");
                }
            }

            System.out.println("Результат: " + result);
            writer.write(operation + ": " + result + "\n");

        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода. Пожалуйста, введите корректные числа.");
        } catch (IllegalArgumentException | ArithmeticException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл.");
        } finally {
            scanner.close();
        }
    }
}
