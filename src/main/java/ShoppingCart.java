import java.util.HashMap;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class ShoppingCart {
    private static int cartCounter = 0;
    private final HashMap<String, LineItem> shoppingCart;
    private final LocalDateTime cartDateTime;
    private static int lineItemCounter = 0;
    private int size = 0;

    public ShoppingCart() {
        shoppingCart = new HashMap<>();
        cartDateTime = LocalDateTime.now();
        cartCounter++;
    }

    public static int getCartCounter() {
        return cartCounter;
    }

    public LineItem getLineItemFromShoppingCart(String EAN) {
        return shoppingCart.get(EAN);

    }

    public boolean isEmpty() {
        return shoppingCart.isEmpty();
    }

    public LocalDateTime getDateTime() {
        return cartDateTime;
    }

    public int size() {
        return size;
    }

    public static int getLineItemCounter() {
        return lineItemCounter;
    }

    public void addItem(Item item) {
        if(!shoppingCart.containsKey(item.getEAN())) {
            shoppingCart.put(item.getEAN(), new LineItem(item, 1));
            size++;
        } else {
            shoppingCart.get(item.getEAN()).increaseQuantity();
            size++;
        }
    }

    public void removeItem(Item item) {
        if(shoppingCart.isEmpty()){
            throw new IllegalArgumentException("The ShoppingCart is empty.");
        }
        if (!shoppingCart.containsKey(item.getEAN())) {
            throw new IllegalArgumentException("No such item exists");
        }
        LineItem tempLineItem = shoppingCart.get(item.getEAN());
        size--;
        if (tempLineItem.getQuantity() == 1) {
            shoppingCart.remove(item.getEAN());
            return;
        }
        tempLineItem.decreaseQuantity();
    }
}
