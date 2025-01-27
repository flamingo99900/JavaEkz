public class Main {
    public static void main(String[] args) {
        int[] array1 = {1, 2, 3};
        int[] array2 = {4, 5, 6};

        // Арифметическое сложение
        InArray arraySum = new ArraySum();
        int[] sumResult = arraySum.addArrays(array1, array2);
        System.out.println("Сложение массивов: ");
        for (int num : sumResult) {
            System.out.print(num + " ");
        }

        System.out.println();

        // Логическая операция ИЛИ
        InArray orArray = new OrArray();
        int[] orResult = orArray.addArrays(array1, array2);
        System.out.println("Логическая операция ИЛИ: ");
        for (int num : orResult) {
            System.out.print(num + " ");
        }
    }
}