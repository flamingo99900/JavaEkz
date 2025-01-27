import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите значения CMYK (через пробел): ");
        double c = scanner.nextDouble();
        double m = scanner.nextDouble();
        double y = scanner.nextDouble();
        double k = scanner.nextDouble();

        ColorModel cmykConverter = new CMYKConverter(c, m, y, k);
        cmykConverter.convert();
    }
}