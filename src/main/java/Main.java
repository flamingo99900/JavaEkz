import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите натуральное число: ");
        int n = scanner.nextInt();

        // Проверка на натуральное число
        if (n <= 0) {
            System.out.println("Пожалуйста, введите натуральное число.");
            return;
        }

        // Преобразуем число в строку, чтобы получить доступ к каждой цифре
        String numberString = Integer.toString(n);

        System.out.println("Цифры в числе " + n + ":");
        for (char digit : numberString.toCharArray()) {
            System.out.println(digit);
        }

        scanner.close();
    }
}