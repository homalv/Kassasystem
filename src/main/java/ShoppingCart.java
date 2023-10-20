import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    private static final int ONE = 1;
    private final HashMap<String, LineItem> shoppingCart;
    private final LocalDateTime cartDateTime;
    private boolean isPaid;

    public ShoppingCart() {
        shoppingCart = new HashMap<>();
        cartDateTime = LocalDateTime.now();
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

    public boolean addItem(Item item) {
        if (item == null) {
            return false;
        }
        String EAN = item.getEAN();
        if (!shoppingCart.containsKey(EAN)) {
            shoppingCart.put(EAN, new LineItem(item, ONE));
        } else {
            shoppingCart.get(EAN).increaseQuantityByOne();
        }
        return true;
    }

    public boolean removeItem(Item item) {
        if (item == null) {
            return false;
        }
        String EAN = item.getEAN();
        if (!shoppingCart.containsKey(EAN)) {
            return false;
        }
        LineItem tempLineItem = shoppingCart.get(EAN);
        if (tempLineItem.getQuantity() == ONE) {
            shoppingCart.remove(EAN);
            return true;
        }
        tempLineItem.decreaseQuantityByOne();
        return true;
    }

    public int numOfItemsInShoppingCart() {
        int counter = 0;
        for (Map.Entry<String, LineItem> entry : shoppingCart.entrySet()) {
            counter += entry.getValue().getQuantity();
        }
        return counter;
    }

    public String getTotalPriceInKronor() {
        long totalPrice = getTotalPriceInOre();
        long kronor = totalPrice / 100;
        long ore = totalPrice % 100;
        return String.format("%d,%02d KR", kronor, ore);
    }

    public long getTotalPriceInOre() {
        long totalPrice = 0;
        for (Map.Entry<String, LineItem> entry : shoppingCart.entrySet()) {
            totalPrice += entry.getValue().getItem().getPrice() * entry.getValue().getQuantity();
        }

        return totalPrice;
    }

    public List<LineItem> getLineItemsForPaidPurchase() {
        if (!isPaid) {
            return null;
        }
        List<LineItem> listOfItems = new ArrayList<>();
        for (Map.Entry<String, LineItem> entry : shoppingCart.entrySet()) {
            listOfItems.add(entry.getValue());
        }
        return listOfItems;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
