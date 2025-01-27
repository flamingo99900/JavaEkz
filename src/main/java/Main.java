// 8. Разработать программу для вывода на экран кубов первых десяти положительных
//чисел.

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        JFrame frame = new JFrame("Задание 8");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false); // только для чтения

        StringBuilder taskText = new StringBuilder(" Кубы первых десяти положительных чисел: \n");

        for (int i = 1; i < 11; i++){
            int result = i * i * i;
            taskText.append(i).append("^3 = ").append(result).append("\n");
        }

        textArea.setText(taskText.toString());
        frame.add(textArea);
        frame.setVisible(true);
    }

}