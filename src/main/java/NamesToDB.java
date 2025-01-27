package org.example;


import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class NamesToDB {
    // JDBC URL, пользователь и пароль
    private static final String JDBC_URL = "jdbc:h2:./testDB";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        // Коллекции с именами людей
        List<String> collection1 = Arrays.asList("Alina", "Masha", "Faina");
        List<String> collection2 = Arrays.asList("David", "Nikola", "Fedor");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            // Создаем таблицу, если её нет
            createTable(connection);

            // Сохранение коллекции в таблицу
            saveNames(connection, collection1, "Collection1");
            saveNames(connection, collection2, "Collection2");

            // Вывод имен
            printNames(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Создание таблицы
    private static void createTable(Connection connection) throws SQLException {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS names (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(255) NOT NULL,
                collection_name VARCHAR(255) NOT NULL
            );
            """;
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        }
    }

    // Сохранение имен
    private static void saveNames(Connection connection, List<String> names, String groupName) throws SQLException {
        String insertSQL = "INSERT INTO names (name, collection_name) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            for (String name : names) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, groupName);
                preparedStatement.executeUpdate();
            }
        }
    }

    // Вывод имен
    private static void printNames(Connection connection) throws SQLException {
        String selectSQL = "SELECT name, collection_name FROM names";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            System.out.println("Имена в таблице:");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String collection = resultSet.getString("collection_name");
                System.out.println(name + " - " + collection);
            }
        }
    }
}
