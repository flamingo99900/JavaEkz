package org.example;

public class ComplexNumber {
    private int real; // Вещественная часть
    private int imaginary; // Мнимая часть

    // Конструктор с заданием вещественной и мнимой частей через массив
    public ComplexNumber(int[] parts) {
        if (parts.length != 2) {
            throw new IllegalArgumentException("Массив должен содержать ровно 2 элемента");
        }
        this.real = parts[0];
        this.imaginary = parts[1];
    }

    // Получение вещественной и мнимой части в виде массива
    public int[] toArray() {
        return new int[]{real, imaginary};
    }

    // Сложение
    public ComplexNumber add(ComplexNumber other) {
        return new ComplexNumber(new int[]{this.real + other.real, this.imaginary + other.imaginary});
    }

    // Вычитание
    public ComplexNumber subtract(ComplexNumber other) {
        return new ComplexNumber(new int[]{this.real - other.real, this.imaginary - other.imaginary});
    }

    // Умножение
    public ComplexNumber multiply(ComplexNumber other) {
        int newReal = this.real * other.real - this.imaginary * other.imaginary;
        int newImaginary = this.real * other.imaginary + this.imaginary * other.real;
        return new ComplexNumber(new int[] {newReal, newImaginary});
    }

    // Переопределение метода toString
    @Override
    public String toString() {
        return real + (imaginary >= 0 ? " + " : " - ") + Math.abs(imaginary) + "i";
    }
}