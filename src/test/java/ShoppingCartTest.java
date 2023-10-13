
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ShoppingCartTest {
private static final String PINEAPPLE = "PINEAPPLE";
private static final int DEFAULT_PRICE = 2000;

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
        Item testItem = new Item(PINEAPPLE, DEFAULT_PRICE);
        shoppingCart.addItem(testItem);
        String testItemEAN = testItem.getEAN();
        assertEquals(testItem, shoppingCart.getLineItemFromShoppingCart(testItemEAN).getItem());
    }

    @Test
    void testGetCartSizeAfterAdding() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new Item(PINEAPPLE, DEFAULT_PRICE));
        shoppingCart.addItem(new Item(PINEAPPLE, DEFAULT_PRICE));
        shoppingCart.addItem(new Item(PINEAPPLE, DEFAULT_PRICE));
        assertEquals(3, shoppingCart.size());
    }

    @Test
    void testGetCartSizeAfterRemoving() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Item testItem = new Item(PINEAPPLE, DEFAULT_PRICE);
        shoppingCart.addItem(testItem);
        shoppingCart.addItem(testItem);
        shoppingCart.addItem(testItem);
        shoppingCart.removeItem(testItem);
        assertEquals(2, shoppingCart.size());
    }

    @Test
    void testAddAndRemoveSingleItem() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Item testItem = new Item(PINEAPPLE, DEFAULT_PRICE);
        shoppingCart.addItem(testItem);
        shoppingCart.removeItem(testItem);
        assertTrue(shoppingCart.isEmpty());
    }

    @Test
    void testQuantityAfterAddAndRemoveOneOfDuplicateItems() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Item testItem = new Item(PINEAPPLE, DEFAULT_PRICE);
        shoppingCart.addItem(testItem);
        shoppingCart.addItem(testItem);
        shoppingCart.removeItem(testItem);
        assertEquals(1, shoppingCart.getLineItemFromShoppingCart(testItem.getEAN()).getQuantity());

    }
    
    

}
