import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DatabaseSetup {
    private static final String JDBC_URL = "jdbc:h2:mem:testdb"; // Временная база данных в памяти
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    public static void setupDatabase() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            // Создание таблицы для хранения результатов
            String createTableSQL = "CREATE TABLE GCD_Results (id INT AUTO_INCREMENT PRIMARY KEY, num1 INT, num2 INT, gcd INT)";
            try (Statement statement = connection.createStatement()) {
                statement.execute(createTableSQL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveGCDResult(int num1, int num2, int gcd) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String insertSQL = "INSERT INTO GCD_Results (num1, num2, gcd) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
                preparedStatement.setInt(1, num1);
                preparedStatement.setInt(2, num2);
                preparedStatement.setInt(3, gcd);
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

