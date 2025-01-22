import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Message> messages = new ArrayList<>();
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        saveMessage(0, "Программа запущена", System.currentTimeMillis()-startTime);
        System.out.println("Программа запущена");

        // Поток для отсчета времени
        Thread timeThread = new Thread(() -> {
            while (System.currentTimeMillis() - startTime < 60000) {
                try {
                    // Выводим время через каждые 5 секунд
                    Thread.sleep(5000);
                    long elapsedTime = System.currentTimeMillis() - startTime;
                    saveMessage(1, "Прошло времени: " + elapsedTime / 1000 + " сек.", System.currentTimeMillis()-startTime);
                    System.out.println("Поток 1 | Прошло времени: " + elapsedTime / 1000 + " сек.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Поток для вывода сообщений каждые 7 секунд
        Thread messageThread1 = new Thread(() -> {
            while (System.currentTimeMillis() - startTime < 60000) {
                try {
                    // Выводим сообщение каждые 7 секунд
                    Thread.sleep(7000);
                    saveMessage(2, "Сообщение от потока 2", System.currentTimeMillis()-startTime);
                    System.out.println("Поток 2 | Сообщение от потока 2");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Поток для вывода сообщений каждые 10 секунд
        Thread messageThread2 = new Thread(() -> {
            while (System.currentTimeMillis() - startTime < 60000) {
                try {
                    // Выводим сообщение каждые 10 секунд
                    Thread.sleep(10000);
                    saveMessage(3, "Сообщение от потока 3", System.currentTimeMillis()-startTime);
                    System.out.println("Поток 3 | Сообщение от потока 3");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Запускаем все потоки
        timeThread.start();
        messageThread1.start();
        messageThread2.start();

        try {
            // Ждем, пока все потоки закончат выполнение
            timeThread.join();
            messageThread1.join();
            messageThread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Configuration cfg = new Configuration();
        cfg.configure();
        try (SessionFactory factory = cfg.buildSessionFactory();
             Session session = factory.openSession()){
            Transaction transaction = session.beginTransaction();
            for (Message message : messages) {
                session.persist(message);
            }
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static void saveMessage(int threadNumber, String message, long date) {
        Message mes = new Message();
        mes.setThreadNum(threadNumber);
        mes.setMessage(message);
        mes.setTime(date);
        messages.add(mes);
    }
}

