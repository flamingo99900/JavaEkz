// 27. Создайте класс Number для конвертации десятичного числа в бинарный,
//восьмеричный, шестнадцатеричный вид. Реализовать в виде статических методов класса. Числа
//вводятся с клавиатуры с запросом в какой численный вид конвертировать.
import java.util.Scanner;

public class Number {

    public static String makeBinary(int number){
        return Integer.toBinaryString(number);
    }

    public static String makeOctal(int number){
        return Integer.toOctalString(number);
    }

    public static String makeHex(int number){
        return Integer.toHexString(number).toUpperCase();
    }

    public static void convert() {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        System.out.print("Введите десятичное число: ");
        int number = sc.nextInt();

        while (flag) {
            System.out.println("В какой формат конвертировать число?");
            System.out.println(" 1. Бинарный \n 2. Восьмеричный \n 3. Шестнадцатеричный \n 4. Выход");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println(number + " в бинарном виде: " + makeBinary(number));
                    break;
                case 2:
                    System.out.println(number + " в восьмеричном виде: " + makeOctal(number));
                    break;
                case 3:
                    System.out.println(number + " в шестнадцатеричном виде: " + makeHex(number));
                    break;
                case 4:
                    flag = false;
                    break;
            }


        }
    }

    public static void main(String[] args) {
        convert();
    }
}
