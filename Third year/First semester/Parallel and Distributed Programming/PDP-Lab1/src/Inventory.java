import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Inventory {
    private final Map<String, Product> products = new HashMap<>();
    private final List<Bill> bills = new ArrayList<>();
    private double totalMoney = 0.0;
    private final Lock moneyLock = new ReentrantLock();
    private final Lock billLock = new ReentrantLock();

    public Inventory(List<Product> productList) {
        for (Product product : productList) {
            products.put(product.getName(), product);
        }
    }

    public void sellProducts(Map<String, Integer> itemsSold) {
        double saleTotal = 0.0;
        Map<String, Integer> soldItems = new HashMap<>();

        for (Map.Entry<String, Integer> entry : itemsSold.entrySet()) {
            String productName = entry.getKey();
            int quantity = entry.getValue();

            Product product = products.get(productName);

            if (product == null) {
                System.out.println("Product " + productName + " does not exist.");
                continue;
            }

            if (!product.isAvailable(quantity)) {
                System.out.println("Not enough quantity for product: " + productName);
                continue;
            }

            product.decreaseQuantity(quantity);
            saleTotal += product.getUnitPrice() * quantity;
            soldItems.put(productName, quantity);
        }

        if (!soldItems.isEmpty()) {
            Bill bill = new Bill(soldItems, saleTotal);
            moneyLock.lock();
            try {
                totalMoney += saleTotal;
            } finally {
                moneyLock.unlock();
            }
            billLock.lock();
            try {
                bills.add(bill);
            } finally {
                billLock.unlock();
            }
        }
    }


    public void checkInventory() {
        double computedMoney = 0.0;
        billLock.lock();
        try {
            for (Bill bill : bills) {
                computedMoney += bill.getTotalPrice();
            }
        } finally {
            billLock.unlock();
        }

        if (computedMoney == totalMoney) {
            System.out.println("Inventory check passed.");
        } else {
            System.out.println("Inventory check failed. Expected: " + computedMoney + ", Found: " + totalMoney);
        }

    }

    public void displayInventory() {
        System.out.println("Current Inventory:");
        products.forEach((name, product) -> {
            System.out.println(name + ": " + product.getQuantity());
        });
        System.out.println("Total money: " + totalMoney);

    }
}
