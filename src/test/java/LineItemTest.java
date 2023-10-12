import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineItemTest {

    @Test
    void testItemNotNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new LineItem(new Item("Banan", 100), 5));
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
}