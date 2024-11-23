import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        List<Product> products = Arrays.asList(
                new Product("Apple", 1000, 1.0),
                new Product("Banana", 900, 0.5),
                new Product("Orange", 750, 0.75)
        );

        Inventory inventory = new Inventory(products);
        int[] threadCounts = {1, 2, 4, 8};

        Thread inventoryCheckThread = new Thread(() -> {
            Random random = new Random();
            while (true) {
                try {
                    int sleepTime = 1000;
                    Thread.sleep(sleepTime);
                    System.out.println("Performing random inventory check...");
                    inventory.checkInventory();
                } catch (InterruptedException e) {
                    //System.out.println("Inventory check thread interrupted.");
                    break;
                }
            }
        });

        inventoryCheckThread.start();

        for (int numThreads : threadCounts) {
            Thread[] threads = new Thread[numThreads];
            long startTime = System.nanoTime();

            for (int i = 0; i < numThreads; i++) {
                threads[i] = new Thread(() -> {
                    Random random = new Random();
                    for (int j = 0; j < 5; j++) {
                        Map<String, Integer> itemsSold = new HashMap<>();
                        itemsSold.put("Apple", random.nextInt(5) + 1);
                        itemsSold.put("Banana", random.nextInt(3) + 1);
                        itemsSold.put("Orange", random.nextInt(2) + 1);

                        itemsSold.forEach((productName, quantity) -> {
                            Product product = products.stream()
                                    .filter(p -> p.getName().equals(productName))
                                    .findFirst()
                                    .orElse(null);
                            if (product != null) {
                                int maxSellable = Math.min(product.getQuantity(), quantity);
                                itemsSold.put(productName, maxSellable);
                            }
                        });
                        inventory.sellProducts(itemsSold);
                        try {
                            Thread.sleep(random.nextInt(500));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            for (Thread thread : threads) {
                thread.start();
            }

            for (Thread thread : threads) {
                thread.join();
            }

            inventory.displayInventory();
            System.out.println("Final inventory check with " + numThreads + " threads:");
            inventory.checkInventory();

            long duration = System.nanoTime() - startTime;
            System.out.println("Execution time with " + numThreads + " threads: " +
                    TimeUnit.NANOSECONDS.toMillis(duration) + " ms");
        }

        inventoryCheckThread.interrupt();
        inventoryCheckThread.join();
    }
}