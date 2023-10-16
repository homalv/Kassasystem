import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.util.Map;

public class ShoppingCart {
    private final HashMap<String, LineItem> shoppingCart;
    private final LocalDateTime cartDateTime;

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
            shoppingCart.put(EAN, new LineItem(item, 1));
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
        if (tempLineItem.getQuantity() == 1) {
            shoppingCart.remove(EAN);
            return true;
        }
        tempLineItem.decreaseQuantityByOne();
        return true;
    }

    public int numbOfItemsInShoppingCart() {
        int counter = 0;
        for (Map.Entry<String, LineItem> entry : shoppingCart.entrySet()) {
            counter += entry.getValue().getQuantity();
        }
        return counter;
    }

    public String getTotalPriceInKronor() {
        long totalPrice = 0;
        for (Map.Entry<String, LineItem> entry : shoppingCart.entrySet()) {
            totalPrice += entry.getValue().getItem().getPrice() * entry.getValue().getQuantity();
        }
        long kronor = totalPrice / 100;
        long ore = totalPrice % 100;
        return String.format("%d,%02d KR", kronor, ore);
    }

    public Long getTotalPriceInOre() {
        long totalPrice = 0;
        for (Map.Entry<String, LineItem> entry : shoppingCart.entrySet()) {
            totalPrice += entry.getValue().getItem().getPrice() * entry.getValue().getQuantity();
        }

        return totalPrice;
    }

    public ArrayList<LineItem> completePurchase(){
        ArrayList<LineItem> listOfItems = new ArrayList<>();
        for (Map.Entry<String, LineItem> entry : shoppingCart.entrySet()){
            listOfItems.add(entry.getValue());
        }
        return listOfItems;
    }
}
