package com.example.task41;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);


        Task2 e2 = new Task2();
        int x = 0;

        String check = "";
        while(!check.equals("9"))

        {
            System.out.println("""
                1. Вывести все таблицы из H2.
                2. Создать таблицу в H2.
                3. Ввести две строки с клавиатуры, результат сохранить в H2 с последующим выводом в консоль.
                4. Подсчитать размер ранее введенных строк, результат сохранить в H2 с последующим выводом в
                консоль.
                5. Объединить две строки в единое целое, результат сохранить в H2 с последующим выводом в
                консоль.
                6. Сравнить две ранее введенные строки, результат сохранить в H2 с последующим выводом в
                консоль.
                7. Удалить таблицу.
                8. Сохранить данные в Excel.
                9. Выход из программы
                """);
            check = sc.next();
            try {
                x = Integer.parseInt(check);
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода");
            }

            switch (x) {
                case 1 -> e2.sql_show("jdbc:h2:mem:blog");
                case 2 -> e2.sql_create();
                case 3 -> e2.exc_2_insert();
                case 4 -> e2.exc_2_1();
                case 5 -> e2.exc_2_2();
                case 6 -> e2.exc_2_3();
                case 7 -> e2.drop("jdbc:h2:mem:blog");
                case 8 -> e2.excel("jdbc:h2:mem:blog");
            }
        }

    }


}
