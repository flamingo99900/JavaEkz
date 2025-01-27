package fa.ru.task_30;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;
import java.sql.*;


public class Main {
    public static void main(String[] args) throws SQLException{
        String url = "jdbc:h2:mem:blog";
        String user = "sa";
        String password = "password";
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите количество чисел:");
        int number = sc.nextInt();
        System.out.println("Введите начало диапазона:");
        int x1 = sc.nextInt();
        System.out.println("Введите конец диапазона:");
        int x2 = sc.nextInt();
        ArrayList<Integer> list1 = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < number; i++) {
            int x = r.nextInt(x1, x2);
            list1.add(x);
        }
        System.out.println(list1);
        System.out.println("Введите число для проверки:");
        int x3 = sc.nextInt();
        if (binarySearch1(list1, x3)) {
            System.out.println("Число " + x3 + " входит в список " + list1);
        }
        else {
            System.out.println("Число " + x3 + " не входит в список " + list1);
        }


        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // Создание таблицы
            String create = "CREATE TABLE IF NOT EXISTS table2 (id INT AUTO_INCREMENT PRIMARY KEY, numbers INT NOT NULL, x INT NOT NULL, result BOOLEAN)";
            try (Statement statement = connection.createStatement()) {
                statement.execute(create);
                System.out.println("Таблица создана или уже существует.");
            }


            // Вставка данных в таблицу
            String query = "INSERT INTO table2 (numbers, x, result) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                for (int i = 0; i < list1.size(); i++){
                    pstmt.setInt(1, list1.get(i));
                    pstmt.setInt(2, x3);
                    pstmt.setBoolean(3, binarySearch1(list1, x3));
                    pstmt.executeUpdate();
                }
                System.out.println("Данные успешно вставлены в таблицу.");
            }


            String query2 = "SELECT * FROM table2";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query2)) {
                System.out.println("Вывод из БД");
                System.out.println("id | numbers");
                while (resultSet.next()) {
                    String column = resultSet.getString("id");
                    String column1 = resultSet.getString("numbers");
                    System.out.println(column + " | " + column1);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static boolean binarySearch1(ArrayList<Integer> numbers_list, int item) {
        numbers_list.sort(Comparator.naturalOrder());
        int sred;
        int first = 0;
        int last = numbers_list.size() - 1;


        while (first <= last) {
            sred = first + (last - first) / 2; // Находим индекс среднего элемента
            if (numbers_list.get(sred) == item) {
                return true; // нашли
            }
            else {
                if (numbers_list.get(sred) > item) {
                    last = sred - 1; // уменьшаем позицию на 1, ищем слева
                } else{
                    first = sred + 1;    // иначе увеличиваем на 1, ищем справа
                }
            }
        }
        return false; // не нашли
    }
}