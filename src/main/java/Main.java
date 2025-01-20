import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure();
        try (SessionFactory factory = cfg.buildSessionFactory();
             Session session = factory.openSession()){
            Scanner scanner = new Scanner(System.in);
            boolean run = true;
            while (run) {
                System.out.println("Введите слово: ");
                String input = scanner.nextLine();
                if (input.equals("q")) {
                    run = false;
                } else {
                    System.out.println("Ваше слово:");
                    for (int i = 0; i < 6; i++) {
                        System.out.println(input);
                    }
                    Word word = new Word();
                    word.setWord(input);
                    Transaction transaction = session.beginTransaction();
                    session.persist(word);
                    transaction.commit();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
