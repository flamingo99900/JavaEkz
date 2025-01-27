import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static SessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string1 = "", string2 = "";

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Вывести все таблицы");
            System.out.println("2. Создать таблицу");
            System.out.println("3. Ввести две строки с клавиатуры и сохранить");
            System.out.println("4. Подсчитать длины строк и сохранить");
            System.out.println("5. Объединить строки и сохранить");
            System.out.println("6. Сравнить строки и сохранить");
            System.out.println("7. Сохранить все данные из БД в Excel");
            System.out.println("8. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine();


            switch (choice) {
                case 1 -> listTables();
                case 2 -> createTable();
                case 3 -> {
                    System.out.println("Введите первую строку (не менее 50 символов):");
                    string1 = scanner.nextLine();
                    System.out.println("Введите вторую строку (не менее 50 символов):");
                    string2 = scanner.nextLine();
                    saveStringsToDatabase(string1, string2);
                }
                case 4 -> calculateStringLengths(string1, string2);
                case 5 -> mergeStrings(string1, string2);
                case 6 -> compareStrings(string1, string2);
                case 7 -> saveToExcel();
                case 8 -> {
                    System.out.println("Выход из программы.");
                    sessionFactory.close();
                    return;
                }
                default -> System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private static void listTables() {
        try (Session session = sessionFactory.openSession()) {
            List<Object[]> tables = session.createNativeQuery("SHOW TABLES").getResultList();
            System.out.println("Таблицы в базе данных:");
            for (Object[] table : tables) {
                System.out.println(table[0]);
            }
        }
    }

    private static void createTable() {
        try (Session session = sessionFactory.openSession()) {
            session.doWork(connection -> {
                connection.createStatement().executeUpdate(
                        "CREATE TABLE IF NOT EXISTS stringdata (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                "string1 VARCHAR(255), " +
                                "string2 VARCHAR(255), " +
                                "result VARCHAR(255))");
                System.out.println("Таблица создана.");
            });
        }
    }

    private static void saveStringsToDatabase(String string1, String string2) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            StringData data = new StringData();
            data.setString1(string1);
            data.setString2(string2);
            session.persist(data);
            tx.commit();
            System.out.println("Строки сохранены в базу данных.");
        }
    }

    private static void calculateStringLengths(String string1, String string2) {
        String result = "Длина строки 1: " + string1.length() + ", Длина строки 2: " + string2.length();
        saveResultToDatabase(string1, string2, result);
        System.out.println(result);
    }

    private static void mergeStrings(String string1, String string2) {
        String result = string1 + string2;
        saveResultToDatabase(string1, string2, result);
        System.out.println("Объединенная строка: " + result);
    }

    private static void compareStrings(String string1, String string2) {
        String result = string1.equals(string2) ? "Строки равны" : "Строки не равны";
        saveResultToDatabase(string1, string2, result);
        System.out.println(result);
    }

    private static void saveResultToDatabase(String string1, String string2, String result) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            StringData data = new StringData();
            data.setString1(string1);
            data.setString2(string2);
            data.setResult(result);
            session.persist(data);
            tx.commit();
        }
    }

    private static void saveToExcel() {
        try (Session session = sessionFactory.openSession()) {
            Query<StringData> query = session.createQuery("FROM StringData", StringData.class);
            List<StringData> dataList = query.list();

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Strings Data");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("String 1");
            headerRow.createCell(2).setCellValue("String 2");
            headerRow.createCell(3).setCellValue("Result");

            int rowNum = 1;
            for (StringData data : dataList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(data.getId());
                row.createCell(1).setCellValue(data.getString1());
                row.createCell(2).setCellValue(data.getString2());
                row.createCell(3).setCellValue(data.getResult());
            }

            try (FileOutputStream fileOut = new FileOutputStream("StringsData.xlsx")) {
                workbook.write(fileOut);
            }
            workbook.close();

            System.out.println("Данные сохранены в файл StringsData.xlsx.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
