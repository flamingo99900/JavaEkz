class RightTriangle extends Triangle {
    // Конструктор
    public RightTriangle(double base, double height) {
        super(base, height, Math.sqrt(base * base + height * height)); // c = √(a² + b²)
    }

    // Переопределение метода для вычисления площади
    @Override
    public double getArea() {
        return (a * b) / 2; // Площадь прямоугольного треугольника
    }
}