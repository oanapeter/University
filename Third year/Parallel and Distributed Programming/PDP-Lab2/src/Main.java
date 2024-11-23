import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static final int[] vector1 = {1, 2 ,3 ,4 , 5};
    private static final int[] vector2 = {6, 7, 8, 9 ,10};
    private static int product = 0;
    private static boolean available = false;
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    public static void main(String[] args) throws InterruptedException {
        Thread producer = new Thread(new Producer());
        Thread consumer = new Thread(new Consumer());

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }

    static class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < vector1.length; i++) {
                lock.lock();
                try {
                    while (available) {
                        condition.await();
                    }
                    product = vector1[i] * vector2[i];
                    available = true;
                    System.out.println("Produced: " + product);
                    condition.signal();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }

            lock.lock();
            try {
                while (available) {
                    condition.await();
                }
                available = true;
                product = Integer.MIN_VALUE;
                condition.signal();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }


    static class Consumer implements Runnable {
        private int sum = 0;

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (!available) {
                        condition.await();
                    }

                    if (product == Integer.MIN_VALUE) {
                        System.out.println("Total scalar product: " + sum);
                        break;
                    }
                    sum += product;
                    System.out.println("Consumed: " + product);
                    available = false;
                    condition.signal();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}