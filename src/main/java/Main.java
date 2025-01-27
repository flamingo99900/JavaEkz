import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите значение x: ");
        double x = scanner.nextDouble();

        Function linear = new LinearFunction(2, 3);
        Function quadratic = new QuadraticFunction(1, -2, 1);

        System.out.println("Значение линейной функции: " + linear.calculate(x));
        System.out.println("Значение квадратичной функции: " + quadratic.calculate(x));
    }
}
