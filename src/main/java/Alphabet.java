import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

@SpringBootApplication
public class Alphabet {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:h2:mem:testdb";
        String username = "sa";
        String password = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String createTableSQL = "CREATE TABLE Alphabet (id INT AUTO_INCREMENT PRIMARY KEY, letter CHAR(1))";
            try (Statement statement = connection.createStatement()) {
                statement.execute(createTableSQL);
            }

            String insertSQL = "INSERT INTO Alphabet (letter) VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
                char letter = 'A';
                while (letter <= 'Z') {
                    preparedStatement.setString(1, String.valueOf(letter));
                    preparedStatement.executeUpdate();
                    letter++;
                }
            }

            String selectSQL = "SELECT * FROM Alphabet";
            try (Statement statement = connection.createStatement();
                 var resultSet = statement.executeQuery(selectSQL)) {
                while (resultSet.next()) {
                    String letter = resultSet.getString("letter");
                    System.out.println(letter);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}