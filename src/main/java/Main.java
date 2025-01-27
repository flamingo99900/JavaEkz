//Написать класс, который при введении даты в формате ДД.ММ.ГГ (к примеру, 22.10.20) выводит номер недели. 
//Даты начиная с 2020 по 2022 годы. К примеру, первая неделя в 2020 году: 1-5 января, вторая неделя – 6-12 января. 
//Значит при вводе 08.01.20 вывод должен быть: Неделя 2.

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy");

        System.out.println("Введите дату в формате ДД.ММ.ГГ (например, 08.01.20).");
        System.out.print("Введите дату: ");
        
        String input = scanner.nextLine();
        
        try {
            // Преобразуем строку в объект LocalDate
            LocalDate date = LocalDate.parse(input, formatter);

            // Проверяем диапазон годов
            if (date.getYear() < 2020 || date.getYear() > 2022) {
                System.out.println("Ошибка: введите дату в диапазоне с 2020 по 2022 год.");
            }

            // Получаем номер недели
            int weekNumber = getWeekNumber(date);

            // Выводим результат
            System.out.println("Неделя " + weekNumber);
        } 
        catch (Exception e) {
            System.out.println("Ошибка: неверный формат даты. Убедитесь, что используете ДД.ММ.ГГ.");
        }
    }

    private static int getWeekNumber(LocalDate date) {
        // Определяем, что неделя начинается с понедельника
        WeekFields weekFields = WeekFields.of(Locale.getDefault());

        // Вычисляем номер недели
        return date.get(weekFields.weekOfWeekBasedYear());
    }
}

