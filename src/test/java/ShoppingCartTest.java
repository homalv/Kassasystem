
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ShoppingCartTest {

    @Test
    public void checkEmptyShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        assertTrue(shoppingCart.isEmpty());
    }

    @Test
    public void checkLocalDateTimeOnShoppingCart(){
        ShoppingCart shoppingCart = new ShoppingCart();
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime shoppingCartDateTime = shoppingCart.getDateTime();

        assertEquals(currentDateTime.toLocalDate(), shoppingCartDateTime.toLocalDate());
        assertEquals(currentDateTime.toLocalTime(), shoppingCartDateTime.toLocalTime());
    }


}
