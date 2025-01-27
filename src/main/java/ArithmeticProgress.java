// Класс арифметической прогрессии
class ArithmeticProgress implements Progress {
    private final double first; // Первый элемент прогрессии
    private final double difference; // Разность прогрессии

    // Конструктор
    public ArithmeticProgress(double first, double difference) {
        this.first = first;
        this.difference = difference;
    }

    @Override
    public double getElement(int n) {
        // Формула n-го члена: a_n = a1 + (n - 1) * d
        return first + (n - 1) * difference;
    }

    @Override
    public double getSum(int n) {
        // Формула суммы: S_n = (n / 2) * (2 * a1 + (n - 1) * d)
        return (n / 2.0) * (2 * first + (n - 1) * difference);
    }
}
