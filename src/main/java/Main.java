import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            numbers.add(random.nextInt(100) + 1);
        }

        numbers.sort(Collections.reverseOrder());

        System.out.println("Первые 10 элементов отсортированного списка:");
        for (int i = 0; i < 10; i++) {
            System.out.println(numbers.get(i));
        }

        Configuration cfg = new Configuration();
        cfg.configure();
        try (SessionFactory factory = cfg.buildSessionFactory();
             Session session = factory.openSession()){
            Transaction transaction = session.beginTransaction();
            for (int i = 0; i < 100; i++) {
                Number num = new Number();
                num.setNumber(numbers.get(i));
                session.persist(num);
            }
            transaction.commit();
        }
    }
}
