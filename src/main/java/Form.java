import java.util.Scanner;

class Form {
    protected String textInput;

    public void inputText() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите текст: ");
        textInput = scanner.nextLine();
    }

    public void submit() {
        System.out.println("Текст отправлен: " + textInput);
    }
}
