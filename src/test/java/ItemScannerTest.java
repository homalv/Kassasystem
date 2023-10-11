import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemScannerTest {
    @Test
    void testFindItemFromEANFindsItem() {
        ItemScanner scanner = new ItemScanner();
        Item item = new Item("Milk", 10000);
        assertNull(scanner.findItem("1234567890123"));
        scanner.setIsItemScannerActive(true);
        assertEquals(item, scanner.findItem("1234567890123"));
    }

    @Test
    void testFindItemReturnsNullIfItemNotFound() {
        ItemScanner scanner = new ItemScanner();
        assertNull(scanner.findItem("5901234123457"));
        scanner.setIsItemScannerActive(true);
        assertNull(scanner.findItem("5901234123457"));
    }

}
