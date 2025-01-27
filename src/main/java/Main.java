//Разработать программу для игры «Угадайка». Программа загадывает случайное число от 1 до 10, требуется его отгадать с трех попыток. 
//После каждой попытки, если результат неверен, игроку выводится сообщение, меньше или больше названное игроком число, чем загаданное. 
//Сет заканчивается или если игрок угадывает число, или если исчерпывает три попытки, не угадав. 
//Игра должна быть выполнена в бесконечном цикле, и продолжается до тех пор, пока на предложение «Сыграем еще раз?» игрок не напишет «Нет».

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Добро пожаловать в игру 'Угадайка'!");

        while (true) {
            // Генерация случайного числа от 1 до 10
            int secretNumber = random.nextInt(10) + 1;
            boolean isGuessed = false;

            System.out.println("Я загадал число от 1 до 10. Попробуйте угадать! У вас есть 3 попытки.");

            for (int attempt = 1; attempt <= 3; attempt++) {
                System.out.print("Попытка " + attempt + ": ");
                int userGuess;

                // Проверка корректности ввода
                try {
                    userGuess = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Введите корректное число от 1 до 10.");
                    attempt--; // Не засчитываем попытку, если ввод некорректен
                    continue;
                }

                // Проверка диапазона
                if (userGuess < 1 || userGuess > 10) {
                    System.out.println("Число должно быть от 1 до 10. Попробуйте ещё раз.");
                    attempt--; // Не засчитываем попытку
                    continue;
                }

                // Проверка ответа
                if (userGuess == secretNumber) {
                    System.out.println("Поздравляю! Вы угадали число " + secretNumber + "!");
                    isGuessed = true;
                    break;
                } else if (userGuess < secretNumber) {
                    System.out.println("Моё число больше.");
                } else {
                    System.out.println("Моё число меньше.");
                }
            }

            // Если число не угадано за 3 попытки
            if (!isGuessed) {
                System.out.println("Вы не угадали. Моё число было: " + secretNumber + ".");
            }

            // Предложение сыграть ещё раз
            System.out.print("Сыграем ещё раз? (Да/Нет): ");
            String playAgain = scanner.nextLine().trim();

            if (playAgain.equalsIgnoreCase("Нет")) {
                System.out.println("Спасибо за игру! До свидания!");
                break;
            } else if (!playAgain.equalsIgnoreCase("Да")) {
                System.out.println("Не понял вашего ответа, но будем считать, что вы хотите продолжить.");
            }
        }

        scanner.close();
    }
}
