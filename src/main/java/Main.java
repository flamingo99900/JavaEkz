public class Main {
    public static void main(String[] args) {
        NeuralNetwork neuralNetwork = new NeuralNetwork();

        double[] inputs = {0.5, -1.5};
        double output = neuralNetwork.forward(inputs);

        System.out.println("Результат сети: " + output);
    }
}
