public class NeuralNetwork {
    private Neuron neuron1;
    private Neuron neuron2;
    private Neuron outputNeuron;

    public NeuralNetwork() {
        // Первый слой: 2 входа для каждого нейрона
        neuron1 = new Neuron(2);
        neuron2 = new Neuron(2);

        // Выходной слой: 2 входа (результаты от neuron1 и neuron2)
        outputNeuron = new Neuron(2);
    }

    // Прямое распространение
    public double forward(double[] inputs) {
        // Выходы первого слоя
        double output1 = neuron1.forward(inputs);
        double output2 = neuron2.forward(inputs);

        // Входы для выходного нейрона
        double[] hiddenOutputs = {output1, output2};

        // Выход сети
        return outputNeuron.forward(hiddenOutputs);
    }
}
