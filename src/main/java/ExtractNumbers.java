import java.util.ArrayList;

public class ExtractNumbers {
    public static void main(String[] args) {
        String input = "I have 3 cats, 4 dogs, and 1 turtle";

        ArrayList<Integer> numbers = new ArrayList<>();

        for (char ch : input.toCharArray()) {
            if (Character.isDigit(ch)) {
                numbers.add(Character.getNumericValue(ch));
            }
        }

        int[] resultArray = numbers.stream().mapToInt(Integer::intValue).toArray();

        System.out.print("Извлеченные числа: ");
        for (int num : resultArray) {
            System.out.print(num + " ");
        }
    }
}
