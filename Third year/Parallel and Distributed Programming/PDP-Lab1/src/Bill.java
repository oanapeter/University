import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bill {
    private final Map<String, Integer> itemsSold;
    private final double totalPrice;

    public Bill(Map<String, Integer> itemsSold, double totalPrice) {
        this.itemsSold = itemsSold;
        this.totalPrice = totalPrice;
    }

    public Map<String, Integer> getItemsSold() {
        return itemsSold;
    }

    public double getTotalPrice() {
        return totalPrice;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bill: \n");
        itemsSold.forEach((item, quantity) -> sb.append(item + ": " + quantity + "\n"));
        sb.append("Total Price: " + totalPrice + "\n");
        return sb.toString();
    }
}
