import java.util.HashMap;
import java.time.LocalDateTime;

public class ShoppingCart {
    private HashMap shoppingCart;
    LocalDateTime cartDateTime;

    public ShoppingCart(){
        shoppingCart = new HashMap();
        cartDateTime = LocalDateTime.now();

    }

    public boolean isEmpty(){
        return shoppingCart.isEmpty();
    }

    public LocalDateTime getDateTime(){
        return cartDateTime;
    }
}
