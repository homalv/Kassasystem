
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.contains;

public class ShoppingCartTest {

    @Test
    void checkEmptyShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        assertTrue(shoppingCart.isEmpty());
    }

    @Test
    void checkLocalDateTimeOnShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime shoppingCartDateTime = shoppingCart.getDateTime();

        assertEquals(currentDateTime.toLocalDate(), shoppingCartDateTime.toLocalDate());
        assertEquals(currentDateTime.toLocalTime(), shoppingCartDateTime.toLocalTime());
    }

    @Test
    void testAddOneItemToCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Item testItem = new Item("PINEAPPLE", 2000);
        shoppingCart.addItem(testItem);
        String testItemEAN = testItem.getEAN();
        assertEquals(testItem, shoppingCart.getLineItemFromShoppingCart(testItemEAN).getItem());
    }

    @Test
    void testGetCartSizeAfterAdding() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new Item("PINEAPPLE", 2000));
        shoppingCart.addItem(new Item("PINEAPPLE", 2000));
        shoppingCart.addItem(new Item("PINEAPPLE", 2000));
        assertEquals(3, shoppingCart.size());
    }

    @Test
    void testGetCartSizeAfterRemoving() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Item testItem = new Item("PINEAPPLE", 2000);
        shoppingCart.addItem(testItem);
        shoppingCart.addItem(testItem);
        shoppingCart.addItem(testItem);
        shoppingCart.removeItem(testItem);
        assertEquals(2, shoppingCart.size());
    }

    @Test
    void testAddAndRemoveSingleItem() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Item testItem = new Item("PINEAPPLE", 2000);
        shoppingCart.addItem(testItem);
        shoppingCart.removeItem(testItem);
        assertTrue(shoppingCart.isEmpty());
    }

    @Test
    void testQuantityAfterAddAndRemoveOneOfDuplicateItems() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Item testItem = new Item("PINEAPPLE", 2000);
        shoppingCart.addItem(testItem);
        shoppingCart.addItem(testItem);
        shoppingCart.removeItem(testItem);
        assertEquals(1, shoppingCart.getLineItemFromShoppingCart(testItem.getEAN()).getQuantity());

    }

}
