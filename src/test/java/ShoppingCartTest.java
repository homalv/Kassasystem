
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class ShoppingCartTest {
    private static final String PINEAPPLE = "PINEAPPLE";
    private static final int DEFAULT_PRICE = 2000;
    private static final String VALID_EAN = "1234567890000";
    private static final String CATEGORY = "Food";

    @Test
    void checkEmptyShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        assertTrue(shoppingCart.isEmpty());
    }

    @Test
    void checkNonEmptyShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new Item(PINEAPPLE, DEFAULT_PRICE, VALID_EAN, CATEGORY));
        assertFalse(shoppingCart.isEmpty());
    }

    @Test
    void checkLocalDateTimeOnShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime shoppingCartDateTime = shoppingCart.getDateTime();
        assertEquals(currentDateTime.toLocalDate(), shoppingCartDateTime.toLocalDate());

        Duration difference = Duration.between(currentDateTime.toLocalTime(), shoppingCartDateTime.toLocalTime());
        long seconds = Math.abs(difference.get(ChronoUnit.SECONDS));
        assertTrue(seconds < 2);
    }


    @Test
    void testAddOneItemToCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Item testItem = new Item(PINEAPPLE, DEFAULT_PRICE, VALID_EAN, CATEGORY);
        shoppingCart.addItem(testItem);
        String testItemEAN = testItem.getEAN();
        assertEquals(testItem, shoppingCart.getLineItemFromShoppingCart(testItemEAN).getItem());
    }

    @Test
    void addItemWIthInvalidType() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Object invalidObject = "Not an Item";
        assertThrows(ClassCastException.class, () -> {
            shoppingCart.addItem((Item) invalidObject);
        });
    }

    @Test // Det här testet är onödigt nu.
    void testGetCartSizeAfterAdding() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new Item(PINEAPPLE, DEFAULT_PRICE, VALID_EAN, CATEGORY));
        shoppingCart.addItem(new Item(PINEAPPLE, DEFAULT_PRICE, VALID_EAN, CATEGORY));
        shoppingCart.addItem(new Item(PINEAPPLE, DEFAULT_PRICE, VALID_EAN, CATEGORY));
        assertEquals(3, shoppingCart.numOfItemsInShoppingCart());
    }

    @Test
    void testGetCartSizeAfterRemoving() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Item testItem = new Item(PINEAPPLE, DEFAULT_PRICE, VALID_EAN, CATEGORY);
        shoppingCart.addItem(testItem);
        shoppingCart.addItem(testItem);
        shoppingCart.addItem(testItem);
        shoppingCart.removeItem(testItem);
        assertEquals(2, shoppingCart.numOfItemsInShoppingCart());
    }

    @Test
    void testAddAndRemoveSingleItem() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Item testItem = new Item(PINEAPPLE, DEFAULT_PRICE, VALID_EAN, CATEGORY);
        shoppingCart.addItem(testItem);
        shoppingCart.removeItem(testItem);
        assertTrue(shoppingCart.isEmpty());
    }

    @Test
    void testRemoveFromEmptyShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Item testItem = new Item(PINEAPPLE, DEFAULT_PRICE, VALID_EAN, CATEGORY);
        assertFalse(shoppingCart.removeItem(testItem));
    }

    // Detta test funkar ännu inte för att alla varor har samma EAN-kod.
    @Test
    void testRemoveItemThatIsNotInShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new Item(PINEAPPLE, DEFAULT_PRICE, VALID_EAN, CATEGORY));
        shoppingCart.addItem(new Item(PINEAPPLE, DEFAULT_PRICE, VALID_EAN, CATEGORY));
        Item testItem = new Item("Book", 8500, "0123456789000", CATEGORY);
        assertFalse(shoppingCart.removeItem(testItem));
    }

    @Test
    void testQuantityAfterAddAndRemoveOneOfDuplicateItems() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Item testItem = new Item(PINEAPPLE, DEFAULT_PRICE, VALID_EAN, CATEGORY);
        shoppingCart.addItem(testItem);
        shoppingCart.addItem(testItem);
        shoppingCart.addItem(testItem);
        shoppingCart.removeItem(testItem);
        assertEquals(2, shoppingCart.numOfItemsInShoppingCart());
    }

    @Test
    void testNullForLineItemsWithPaidPurchase(){
        ShoppingCart shoppingCart = new ShoppingCart();
        Item item1 = mock(Item.class);
        Item item2 = mock(Item.class);
        shoppingCart.addItem(item1);
        shoppingCart.addItem(item2);
        shoppingCart.setPaid(false);
        List<LineItem>lineItems = shoppingCart.getLineItemsForPaidPurchase();

        assertNull(lineItems);
    }

    @Test
    void testRemoveItemWithNullItem() {
        ShoppingCart shoppingCart = new ShoppingCart();
        boolean removed = shoppingCart.removeItem(null);
        assertFalse(removed);
        assertTrue(shoppingCart.isEmpty());
    }

    @Test
    void testAddItemWithNull(){
        ShoppingCart shoppingCart = new ShoppingCart();
        boolean empty = shoppingCart.addItem(null);
        assertFalse(empty);
    }

    @Test
    void testGetTotalPriceInShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Item testItem = new Item(PINEAPPLE, 2050, VALID_EAN, CATEGORY);
        shoppingCart.addItem(testItem);
        shoppingCart.addItem(testItem);
        shoppingCart.addItem(testItem);
        assertEquals("61,50 KR", shoppingCart.getStringWithTotalPriceInKronor());
    }

    @Test
    void testGetTotalPriceInShoppingOre() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Item testItem = new Item(PINEAPPLE, 2050, VALID_EAN, CATEGORY);
        shoppingCart.addItem(testItem);
        shoppingCart.addItem(testItem);
        shoppingCart.addItem(testItem);
        assertEquals(6150L, shoppingCart.getTotalPriceInOre());

    }

    @Test
    void testCompletePurchaseReturnsArrayListOfItems(){
        ShoppingCart shoppingCart = new ShoppingCart();
        Item testItem = new Item(PINEAPPLE, 2050, VALID_EAN, CATEGORY);
        shoppingCart.addItem(testItem);
        shoppingCart.addItem(testItem);
        ArrayList<LineItem> purchasedItems = new ArrayList<>();
        shoppingCart.setPaid(true);
        assertNotNull(shoppingCart.getLineItemsForPaidPurchase());
        for (LineItem item : purchasedItems) {
            assertTrue(item instanceof LineItem, "Each element should be a LineItem.");
        }
    }

    @Test
    void testCompletePurchaseCorrectAmountOfItems() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Item testItem = new Item(PINEAPPLE, 2050, VALID_EAN, CATEGORY);
        shoppingCart.addItem(testItem);
        shoppingCart.addItem(testItem);
        shoppingCart.setPaid(true);
        List<LineItem> purchasedItems = shoppingCart.getLineItemsForPaidPurchase();
        int amount =0;
        for(LineItem item : purchasedItems){
            amount += item.getQuantity();
        }
        assertEquals(2,amount);
    }

    @Test
    void testCompletePurchaseCorrectAmountOfLineItems() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Item testItem = new Item(PINEAPPLE, 2050,"1234567890011", CATEGORY);
        Item testItem2 = new Item("Book", 15000,"1234567890012", CATEGORY);
        Item testItem3 = new Item("Juice", 4500, "1234567890013", CATEGORY);
        shoppingCart.addItem(testItem);
        shoppingCart.addItem(testItem2);
        shoppingCart.addItem(testItem3);
        shoppingCart.addItem(testItem2);
        shoppingCart.setPaid(true);
        List<LineItem> purchasedItems = shoppingCart.getLineItemsForPaidPurchase();
        int amount =purchasedItems.size();
        assertEquals(3,amount);
    }


    @Test
    public void testSetPaidToTrueAndChecksStatus() {
        ShoppingCart cart = new ShoppingCart();
        cart.setPaid(true);
        assertTrue(cart.isPaid());
    }

    @Test
    public void testSetPaidToFalseAndChecksStatus() {
        ShoppingCart cart = new ShoppingCart();
        cart.setPaid(true);
        cart.setPaid(false);
        assertFalse(cart.isPaid());
    }

}
