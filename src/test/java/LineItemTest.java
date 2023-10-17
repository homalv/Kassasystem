import item.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineItemTest {

    @Test
    void testItemNullThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new LineItem(null, 5));
    }

    @Test
    void testZeroQuantityThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new LineItem(new Item("Banan", 100), 0));
    }

    @Test
    void testNegativeQuantityThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new LineItem(new Item("Banan", 100), -1));
    }

    @Test
    void testGetPriceReturnCorrect() {
        LineItem lineItem = new LineItem(new Item("BANAN", 2000), 5);
        assertEquals(10000, lineItem.getPrice());
    }
}