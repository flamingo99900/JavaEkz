public class Neuron {
    private double[] weights; // Веса нейрона
    private double bias;      // Смещение (bias)

    public Neuron(int inputSize) {
        // Инициализация весов случайными значениями
        weights = new double[inputSize];
        for (int i = 0; i < inputSize; i++) {
            weights[i] = Math.random() - 0.5; // Диапазон от -0.5 до 0.5
        }
        bias = Math.random() - 0.5; // Смещение
    }

    // Прямое распространение с сигмоидной активацией
    public double forward(double[] inputs) {
        if (inputs.length != weights.length) {
            throw new IllegalArgumentException("Размер входов должен совпадать с количеством весов.");
        }

        // Взвешенная сумма
        double sum = bias;
        for (int i = 0; i < inputs.length; i++) {
            sum += inputs[i] * weights[i];
        }

        // Функция активации (сигмоида)
        return sigmoid(sum);
    }

    // Сигмоидная функция активации
    private double sigmoid(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }
}
