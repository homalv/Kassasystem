import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    // Price
    @Test
    void testCreatesItemWithName() {
        Item i = new Item("AN ITEM", 1000);
        assertEquals("AN ITEM", i.getName());
    }

    // Name
    @Test
    void testCreatesItemWithCorrectPrice() {
        Item i = new Item("AN ITEM", 1000);
        assertEquals(1000, i.getPrice());
    }

    // (IN) Null : null
    // (IN) Empty : ""
    // (IN) Blank : "   "
    // (IN) Non-alphanumeric characters include : ==> FAIL
    // (IN) str.length() > 20
    // (V) Trailing spaces : "    AN ITEM      " => TRIMS
    // (V) Space in between:  "AN     ITEM" => "AN ITEM"
    // (V) Trailing && in between : "    AN     ITEM    "
    // (V)


    // EAN
    // Item category




}