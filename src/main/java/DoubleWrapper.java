package org.example;

public class DoubleWrapper {
    private double value;

    public DoubleWrapper(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public static double add(double a, double b) {
        return a + b;
    }

    public static double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        return a / b;
    }

    public static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    @Override
    public String toString() {
        return "DoubleWrapper{value=" + value + "}";
    }

    public static void main(String[] args) {
        DoubleWrapper num1 = new DoubleWrapper(5.0);
        DoubleWrapper num2 = new DoubleWrapper(2.0);

        // Использование статических методов
        double sum = DoubleWrapper.add(num1.getValue(), num2.getValue());
        double division = DoubleWrapper.divide(num1.getValue(), num2.getValue());
        double exponentiation = DoubleWrapper.power(num1.getValue(), num2.getValue());

        // Вывод результатов
        System.out.println("Сложение: " + sum);
        System.out.println("Деление: " + division);
        System.out.println("Возведение в степень: " + exponentiation);
    }
}
