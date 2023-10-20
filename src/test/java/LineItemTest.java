import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineItemTest {
    private static final String VALID_EAN = "1234567890000";
    private static final String CATEGORY = "Food";

    @Test
    void testItemNullThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new LineItem(null, 5));
    }

    @Test
    void testZeroQuantityThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new LineItem(new Item("Banan", 100, VALID_EAN, CATEGORY), 0));
    }

    @Test
    void testNegativeQuantityThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new LineItem(new Item("Banan", 100, VALID_EAN, CATEGORY), -1));
    }

    @Test
    void testGetPriceReturnCorrect() {
        LineItem lineItem = new LineItem(new Item("BANAN", 2000, VALID_EAN, CATEGORY), 5);
        assertEquals(10000, lineItem.getPrice());
    }


}