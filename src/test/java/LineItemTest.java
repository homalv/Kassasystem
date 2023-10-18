import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineItemTest {
    private static String VALID_EAN = "1234567890000";

    @Test
    void testItemNullThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new LineItem(null, 5));
    }

    @Test
    void testZeroQuantityThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new LineItem(new Item("Banan", 100, VALID_EAN), 0));
    }

    @Test
    void testNegativeQuantityThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new LineItem(new Item("Banan", 100, VALID_EAN), -1));
    }

    @Test
    void testGetPriceReturnCorrect() {
        LineItem lineItem = new LineItem(new Item("BANAN", 2000, VALID_EAN), 5);
        assertEquals(10000, lineItem.getPrice());
    }
}