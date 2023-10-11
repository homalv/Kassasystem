import java.util.HashMap;
import java.time.LocalDateTime;

public class ShoppingCart {
    private final HashMap<Integer, Item> shoppingCart;
    private final LocalDateTime cartDateTime;
    private static int lineItemCounter = 0;

    public ShoppingCart(){
        shoppingCart = new HashMap<>();
        cartDateTime = LocalDateTime.now();
    }

    public boolean isEmpty(){
        return shoppingCart.isEmpty();
    }

    public LocalDateTime getDateTime(){
        return cartDateTime;
    }

    public int size() {
        return shoppingCart.size();
    }

    public void addItem(Item item) {
        shoppingCart.put(lineItemCounter, item);
        lineItemCounter++;
    }
}
