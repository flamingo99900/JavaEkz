public class Main {
    public static void main(String[] args) {
        // Пример работы с арифметической прогрессией
        Progress arithmetic = new ArithmeticProgress(2, 3); // Первый элемент: 2, разность: 3
        System.out.println("Арифметическая прогрессия:");
        System.out.println("5-й элемент: " + arithmetic.getElement(5)); // Ожидаем 14
        System.out.println("Сумма первых 5 элементов: " + arithmetic.getSum(5)); // Ожидаем 40

        // Пример работы с геометрической прогрессией
        Progress geometric = new GeometricProgress(2, 2); // Первый элемент: 2, знаменатель: 2
        System.out.println("\nГеометрическая прогрессия:");
        System.out.println("5-й элемент: " + geometric.getElement(5)); // Ожидаем 32
        System.out.println("Сумма первых 5 элементов: " + geometric.getSum(5)); // Ожидаем 62
    }
}
