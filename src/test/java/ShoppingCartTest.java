
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ShoppingCartTest {

    @Test
    void checkEmptyShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        assertTrue(shoppingCart.isEmpty());
    }

    @Test
    void checkLocalDateTimeOnShoppingCart(){
        ShoppingCart shoppingCart = new ShoppingCart();
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime shoppingCartDateTime = shoppingCart.getDateTime();

        assertEquals(currentDateTime.toLocalDate(), shoppingCartDateTime.toLocalDate());
        assertEquals(currentDateTime.toLocalTime(), shoppingCartDateTime.toLocalTime());
    }

    @Test
    void testAddItemToCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new Item("PINEAPPLE", 2000));
        assertEquals(1, shoppingCart.size());
    }

    @Test
    void testGetCartSize() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new Item("PINEAPPLE", 2000));
        shoppingCart.addItem(new Item("PINEAPPLE", 2000));
        shoppingCart.addItem(new Item("PINEAPPLE", 2000));
        assertEquals(3, shoppingCart.size());
    }


}
