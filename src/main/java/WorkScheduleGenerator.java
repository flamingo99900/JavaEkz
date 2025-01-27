import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkScheduleGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Запрос у пользователя ввод даты
        System.out.print("Введите дату (в формате ГГГГ-MM-ДД): ");
        String inputDate = scanner.nextLine();

        // Преобразуем введенную дату в LocalDate
        LocalDate startDate = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Проверяем, что введенная дата находится в 2022 году
        if (startDate.getYear() != 2022) {
            System.out.println("Ошибка: Введите дату в 2022 году.");
            return;
        }

        // Генерируем расписание
        List<LocalDate> schedule = generateWorkSchedule(startDate);

        // Печатаем расписание
        System.out.println("Расписание на месяц:");
        for (LocalDate date : schedule) {
            System.out.println(date);
        }

        scanner.close();
    }

    private static List<LocalDate> generateWorkSchedule(LocalDate startDate) {
        List<LocalDate> workDays = new ArrayList<>();

        // Определяем первый и последний дни месяца
        LocalDate firstDayOfMonth = startDate.withDayOfMonth(1);
        LocalDate lastDayOfMonth = startDate.withDayOfMonth(startDate.lengthOfMonth());

        LocalDate currentDate = firstDayOfMonth;

        // Генерация рабочего расписания
        while (!currentDate.isAfter(lastDayOfMonth)) {
            // Проверяем на рабочий день
            if (currentDate.getDayOfWeek().getValue() == 7) { // Если это воскресенье
                currentDate = currentDate.plusDays(1); // Переносим на понедельник
            } else {
                // Если текущий день - рабочий
                if (workDays.isEmpty() || currentDate.isAfter(workDays.get(workDays.size() - 1).plusDays(3))) {
                    workDays.add(currentDate);
                }
                currentDate = currentDate.plusDays(1);
            }
        }
        return workDays;
    }
}
