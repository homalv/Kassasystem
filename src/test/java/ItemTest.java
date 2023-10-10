import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    // Price
    @Test
    void testCreatesItem() {
        Item i = new Item("AN ITEM", 1000);
        assertEquals(1000, i.getPrice());
        assertEquals("AN ITEM", i.getName());
    }

    // Name
    // EAN
    // Item category




}