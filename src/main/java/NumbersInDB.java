import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class NumbersInDB{
    private static final String DB_URL = "jdbc:h2:./testdb";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) throws SQLException {
        System.out.println("""
                 Выберите дейтсвие:\s
                1. Вывести все таблицы из MySQL.
                2. Создать таблицу в MySQL.
                3. Сложение чисел, результат сохранить в MySQL с последующим выводом в консоль.
                4. Вычитание чисел, результат сохранить в MySQL с последующим выводом в консоль.
                5. Умножение чисел, результат сохранить в MySQL с последующим выводом в консоль.
                6. Деление чисел, результат сохранить в MySQL с последующим выводом в консоль.
                7. Деление чисел по модулю (остаток), результат сохранить в MySQL с последующим
                выводом в консоль.
                8. Возведение числа в модуль, результат сохранить в MySQL с последующим выводом в
                консоль.
                9. Возведение числа в степень, результат сохранить в MySQL с последующим выводом в
                консоль.
                10. Сохранить все данные (вышеполученные результаты) из MySQL в Excel и вывести на экран""");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя таблицы с которой хотели бы работать");
        String tablename = scanner.nextLine();

        boolean exitRequested = false;

        while (!exitRequested) {

            System.out.println("Выберите действие (11 выход из программы):");
            while (!scanner.hasNextInt()) {
                System.out.println("Ошибка ввода! Введите число от 1 до 11 (11 выход из программы):");
                scanner.next();
            }

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    showTables();
                    break;
                case 2:
                    System.out.println("Введите имя таблицы, которую хотите создать: ");
                    createTable(tablename);
                    break;
                case 3:
                    int A = scanner.nextInt();
                    int B = scanner.nextInt();
                    sum(tablename, A, B);
                    break;
                case 4:
                    A = scanner.nextInt();
                    B = scanner.nextInt();
                    subtract(tablename, A, B);
                    break;
                case 5:
                    A = scanner.nextInt();
                    B = scanner.nextInt();
                    multiply(tablename, A, B);
                    break;
                case 6:
                    A = scanner.nextInt();
                    B = scanner.nextInt();
                    divide(tablename, A, B);
                    break;
                case 7:
                    A = scanner.nextInt();
                    B = scanner.nextInt();
                    ostatok(tablename, A, B);
                    break;
                case 8:
                    A = scanner.nextInt();
                    module(tablename, A);
                    break;
                case 9:
                    A = scanner.nextInt();
                    B = scanner.nextInt();
                    power(tablename, A, B);
                    break;
                case 10:
                    System.out.println("Введите имя файла: ");
                    String filename = scanner.next();
                    exportExcel(tablename, filename);
                case 11:
                    exitRequested = true;
                    continue;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, выберите число от 1 до 11. Чтобы выйти введите 11");
                    break;
            }
        }
    }

    public static void showTables() {
        try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)){
            System.out.println("Успешное подключение. ");

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("Show Tables");
            System.out.println("Таблицы из текущей базы данных. ");
            while (rs.next()) {
                System.out.print(rs.getString(1)); // print нужен чтобы выводились не через строку,
                // так как ей присуща \n
                System.out.println();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTable(String tablename) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)){
            System.out.println("Успешное подключение.");

                String createQuery = "CREATE TABLE IF NOT EXISTS " + tablename + " (NUMBER_1 INT, NUMBER_2 INT, RES DOUBLE)";
            Statement statement = connection.createStatement();
            statement.executeUpdate(createQuery);
            System.out.println("Таблица успешно создана!");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void sum(String tablename, int A, int B)
    {
        double res = A + B;
        insertSQL(tablename, A, B, res);
        showData(tablename);
    }
    public static void subtract(String tablename, int A, int B)
    {
        double res = A - B;
        insertSQL(tablename, A, B, res);
        showData(tablename);
    }
    public static void multiply(String tablename, int A, int B)
    {
        double res = A * B;
        insertSQL(tablename, A, B, res);
        showData(tablename);
    }
    public static void divide(String tablename, int A, int B)
    {
        double res = (double) A / B;
        insertSQL(tablename, A, B, res);
        showData(tablename);
    }
    public static void ostatok(String tablename, int A, int B)
    {
        double res = A % B;
        insertSQL(tablename, A, B, res);
        showData(tablename);
    }
    public static void module(String tablename, int A)
    {
        double res = abs(A);
        insertSQL(tablename, A, 0, res);
        showData(tablename);
    }
    public static void power(String tablename, int A, int B)
    {
        long res = (long) pow(A, B);
        insertSQL(tablename, A, B, res);
        showData(tablename);
    }

    public static void exportExcel(String tableName, String fileName) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Успешное подключение.");

            String query = "SELECT * FROM " + tableName;
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            System.out.println("Вывод текущего состояния таблицы " + tableName);
            while (rs.next()) {
                int num1_new = rs.getInt("NUMBER_1");
                int num2_new = rs.getInt("NUMBER_2");
                double res_new = rs.getDouble("RES");
                System.out.printf("%d %d %.2f%n", num1_new, num2_new, res_new);
            }

            // Создание нового Excel файла
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet(tableName);
            Row headerRow = sheet.createRow(0);

            // Получаем метаданные таблицы для заголовков колонок
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Добавляем заголовки в первый ряд
            for (int i = 1; i <= columnCount; i++) {
                Cell cell = headerRow.createCell(i - 1);
                cell.setCellValue(metaData.getColumnName(i));
            }

            // Добавляем данные в Excel
            int rowNum = 1; // Начинаем с первой строки после заголовков
            while (rs.next()) {
                Row row = sheet.createRow(rowNum++);
                for (int i = 1; i <= columnCount; i++) {
                    Cell cell = row.createCell(i - 1);
                    cell.setCellValue(rs.getString(i));
                }
            }

            // Записываем Excel файл
            FileOutputStream fileOut = new FileOutputStream(fileName);
            workbook.write(fileOut);
            workbook.close();
            System.out.println("Данные успешно записаны в Excel файл: " + fileName);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void insertSQL(String tablename, int NUMBER_1, int NUMBER_2, double Result) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Connection done");

            String createQuery = "CREATE TABLE IF NOT EXISTS " + tablename + " (NUMBER_1 INT, NUMBER_2, RES DOUBLE)";
            Statement statement = connection.createStatement();
            statement.executeUpdate(createQuery);
            System.out.println("Таблица успешно создана!");

            String insertQuery = "INSERT INTO " + tablename + " (NUMBER_1 INT, NUMBER_2, RES) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery);
            stmt.setInt(1, NUMBER_1);
            stmt.setInt(2, NUMBER_2);
            stmt.setDouble(3, Result);
            stmt.executeUpdate();

            System.out.println("Данные успешно добавлены в таблицу " + tablename);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void showData(String tablename) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Connection done");

            String showQuery = "SELECT * FROM " + tablename;
            PreparedStatement statement = connection.prepareStatement(showQuery);
            ResultSet result = statement.executeQuery();

            System.out.println("Данные находящиеся в таблице " + tablename + ":");
            while (result.next()) {
                System.out.print(result.getInt("NUMBER_1") + " ");
                System.out.print(result.getInt("NUMBER_2") + " ");
                System.out.print(result.getDouble("RES") + " ");
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
