// 38. Разработать программу шифровки-дешифровки
// по алгоритму AES-128. Данные берутся из файла,
// зашифрованные данные сохраняются в указанный файл.
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    private static final String ALGORITHM = "AES";
    private static final int KEY_SIZE = 128;

    public static void main(String[] args) {
        String inputFile = "input.txt"; // Пути к файлам
        String encryptedFile = "encrypted.txt";
        String decryptedFile = "decrypted.txt";

        try {
            // Создание тестового входного файла с текстом для шифрования
            createTestFile(inputFile);
            // Генерация ключа
            SecretKey secretKey = generateKey();
            // Шифрование данных из файла
            byte[] encryptedData = encryptFile(inputFile, secretKey);
            writeFile(encryptedFile, encryptedData);
            System.out.println("Файл успешно зашифрован: " + encryptedFile);
            // Дешифрование данных из файла
            byte[] decryptedData = decryptData(encryptedData, secretKey);
            writeFile(decryptedFile, decryptedData);
            System.out.println("Файл успешно расшифрован: " + decryptedFile);
            // Вывод содержимого расшифрованного файла
            System.out.println("Содержимое расшифрованного файла:");
            System.out.println(new String(decryptedData));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Создание тестового файла
    private static void createTestFile(String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Тестовая строка для шифрования.");
        }
    }

    // Генерация ключа AES
    private static SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(KEY_SIZE);
        return keyGenerator.generateKey();
    }

    // Шифрование данных из файла
    private static byte[] encryptFile(String filePath, SecretKey secretKey) throws Exception {
        byte[] fileContent = readFile(filePath);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(fileContent);
    }

    // Дешифрование данных
    private static byte[] decryptData(byte[] encryptedData, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(encryptedData);
    }

    // Чтение данных из файла
    private static byte[] readFile(String filePath) throws IOException {
        return Files.readAllBytes(Paths.get(filePath));
    }

    // Запись данных в файл
    private static void writeFile(String filePath, byte[] data) throws IOException {
        Files.write(Paths.get(filePath), data);
    }
}

