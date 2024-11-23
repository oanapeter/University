import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Product {
    private final String name;
    private int quantity;
    private final double unitPrice;
    private final Lock lock;

    public Product(String name, int quantity, double unitPrice) {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.lock = new ReentrantLock(); // Initialize the ReentrantLock
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public boolean isAvailable(int requestedQuantity) {
        return quantity >= requestedQuantity;
    }

    public void decreaseQuantity(int quantity) {
        lock.lock();
        this.quantity -= quantity;
        lock.unlock();
    }

}
