public class Main {
    public static void main(String[] args) {
        Worker ivan = new Worker();
        ivan.setName("Иван");
        ivan.setAge(25);
        ivan.setSalary(1000);

        Worker vasya = new Worker();
        vasya.setName("Вася");
        vasya.setAge(26);
        vasya.setSalary(2000);

        double total = ivan.getSalary() + vasya.getSalary();
        System.out.println("Сумма зарплат Ивана и Васи: " + total);
    }
}
