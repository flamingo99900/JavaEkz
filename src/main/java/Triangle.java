class Triangle {
    protected double a; // Сторона a
    protected double b; // Сторона b
    protected double c; // Сторона c

    // Конструктор
    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    // Метод для вычисления периметра
    public double getPerimeter() {
        return a + b + c;
    }

    // Метод для вычисления площади по формуле Герона
    public double getArea() {
        double s = getPerimeter() / 2; // Полупериметр
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
}



