class GeometricProgress implements Progress {
    private final double first; // Первый элемент прогрессии
    private final double ratio; // Знаменатель прогрессии

    // Конструктор
    public GeometricProgress(double first, double ratio) {
        this.first = first;
        this.ratio = ratio;
    }

    @Override
    public double getElement(int n) {
        // Формула n-го члена: a_n = a1 * r^(n - 1)
        return first * Math.pow(ratio, n - 1);
    }

    @Override
    public double getSum(int n) {
        // Формула суммы:
        // Для |r| != 1: S_n = a1 * (1 - r^n) / (1 - r)
        // Для r = 1: S_n = n * a1
        if (ratio == 1) {
            return n * first;
        }
        return first * (1 - Math.pow(ratio, n)) / (1 - ratio);
    }
}

