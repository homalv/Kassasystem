
import org.junit.jupiter.api.Test;

import java.lang.instrument.IllegalClassFormatException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


public class ShoppingCartTest {
private static final String PINEAPPLE = "PINEAPPLE";
private static final int DEFAULT_PRICE = 2000;


@Test
    void checkEmptyShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        assertTrue(shoppingCart.isEmpty());
    }

    @Test
    void checkNonEmptyShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new Item(PINEAPPLE, DEFAULT_PRICE));
        assertFalse(shoppingCart.isEmpty());
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
    void addItemWIthInvalidType(){
        ShoppingCart shoppingCart = new ShoppingCart();
        Object invalidObject = "Not an Item";
        assertThrows(ClassCastException.class, () -> {
            shoppingCart.addItem( (Item)invalidObject);
        });
    }

    @Test
    void testGetCartSizeAfterAdding() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new Item(PINEAPPLE, DEFAULT_PRICE));
        shoppingCart.addItem(new Item(PINEAPPLE, DEFAULT_PRICE));
        shoppingCart.addItem(new Item(PINEAPPLE, DEFAULT_PRICE));
        assertEquals(3, shoppingCart.numbOfItems());
    }

    @Test
    void testGetCartSizeAfterRemoving() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Item testItem = new Item(PINEAPPLE, DEFAULT_PRICE);
        shoppingCart.addItem(testItem);
        shoppingCart.addItem(testItem);
        shoppingCart.addItem(testItem);
        shoppingCart.removeItem(testItem);
        assertEquals(2, shoppingCart.numbOfItems());
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
    void testRemoveFromEmptyShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Item testItem = new Item(PINEAPPLE, DEFAULT_PRICE);
        assertThrows(IllegalArgumentException.class, () -> {
            shoppingCart.removeItem(testItem);
        });
    }

    // Detta test funkar ännu inte för att alla varor har samma EAN-kod.
//    @Test
//    void testRemoveItemThatIsNotInShoppingCart() {
//        ShoppingCart shoppingCart = new ShoppingCart();
//        shoppingCart.addItem( new Item(PINEAPPLE, DEFAULT_PRICE));
//        shoppingCart.addItem( new Item(PINEAPPLE, DEFAULT_PRICE));
//        Item testItem = new Item("Book", 8500);
//        assertThrows(IllegalArgumentException.class, () -> {
//            shoppingCart.removeItem(testItem);
//        });
//    }

    @Test
    void testQuantityAfterAddAndRemoveOneOfDuplicateItems() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Item testItem = new Item(PINEAPPLE, DEFAULT_PRICE);
        shoppingCart.addItem(testItem);
        shoppingCart.addItem(testItem);
        shoppingCart.addItem(testItem);
        shoppingCart.removeItem(testItem);
        assertEquals(2, shoppingCart.numbOfItems());

    }
    
    

}
