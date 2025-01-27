public class Main {
    public static void main(String[] args) {
        // Создаем треугольник
        Triangle triangle = new Triangle(3, 4, 5);
        System.out.println("Треугольник:");
        System.out.println("Периметр: " + triangle.getPerimeter());
        System.out.println("Площадь: " + triangle.getArea());

        // Создаем прямоугольный треугольник
        RightTriangle rightTriangle = new RightTriangle(3, 4);
        System.out.println("\nПрямоугольный треугольник:");
        System.out.println("Периметр: " + rightTriangle.getPerimeter());
        System.out.println("Площадь: " + rightTriangle.getArea());
    }
}