import java.util.Scanner;

class SmartForm extends Form {
    private String password;

    public void inputPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите пароль: ");
        password = scanner.nextLine();
    }

    public void displayPassword() {
        System.out.println("Сохранённый пароль: " + password);
    }

    @Override
    public void submit() {
        System.out.println("Текст отправлен: " + textInput);
        System.out.println("Пароль отправлен: " + password);
    }
}

