import java.util.HashMap;
import java.util.Map;

public class Task_4 {
    private static final Map<String, Integer> numberMap = new HashMap<>();
    private static final Map<Integer, String> reverseNumberMap = new HashMap<>();

    static {
        String[] numbers = {"ноль", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"};
        for (int i = 0; i < numbers.length; i++) {
            numberMap.put(numbers[i], i);
            reverseNumberMap.put(i, numbers[i]);
        }
    }

    public static String calculate(String expression) {
        String[] parts = expression.split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат выражения");
        }

        Integer num1 = numberMap.get(parts[0]);
        String operation = parts[1];
        Integer num2 = numberMap.get(parts[2]);

        if (num1 == null || num2 == null) {
            throw new IllegalArgumentException("Неверное число в выражении");
        }

        int result;
        switch (operation) {
            case "плюс":
                result = num1 + num2;
                break;
            case "минус":
                result = num1 - num2;
                break;
            case "умножить":
                result = num1 * num2;
                break;
            default:
                throw new IllegalArgumentException("Неверная операция");
        }

        return reverseNumberMap.getOrDefault(result, "неизвестно");
    }

    public static void main(String[] args) {
        System.out.println(calculate("пять плюс четыре"));  // девять
        System.out.println(calculate("два умножить три"));  // шесть
        System.out.println(calculate("семь минус один"));   // шесть
    }
}
