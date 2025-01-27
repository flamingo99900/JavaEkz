import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите количество чисел:");
        int numberOfDigits = sc.nextInt();
        System.out.println("Введите начало диапазона:");
        int start = sc.nextInt();
        System.out.println("Введите конец диапазона:");
        int end = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < numberOfDigits; i++) {
            int x = r.nextInt(start, end);
            list.add(x);
        }
        System.out.println(list);
        System.out.println("Введите число для проверки:");
        int x = sc.nextInt();
        boolean result = binarySearch(list, x);
        if (result) {
            System.out.println("Число " + x + " входит в список " + list);
        } else {
            System.out.println("Число " + x + " не входит в список " + list);
        }

        Configuration cfg = new Configuration();
        cfg.configure();
        try (SessionFactory factory = cfg.buildSessionFactory();
             Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Result rMessage = new Result();
            rMessage.setNumbers(list.toString());
            rMessage.setX(x);
            rMessage.setResult(result);
            session.persist(rMessage);
            transaction.commit();

            List<Result> all = session.createQuery("FROM Result", Result.class).getResultList();
            all.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean binarySearch(List<Integer> numbersList, int item) {
        numbersList.sort(Comparator.naturalOrder());
        int sred;
        int first = 0;
        int last = numbersList.size() - 1;

        while (first <= last) {
            sred = first + (last - first) / 2; // Находим индекс среднего элемента
            if (numbersList.get(sred) == item) {
                return true; // нашли
            }
            else {
                if (numbersList.get(sred) > item) {
                    last = sred - 1; // уменьшаем позицию на 1, ищем слева
                } else{
                    first = sred + 1;    // иначе увеличиваем на 1, ищем справа
                }
            }
        }
        return false; // не нашли
    }
}
