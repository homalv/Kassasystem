import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AssortmentTest {

    private static final String PINEAPPLE_EAN = "1234567890043";
    private static final String NON_PRESENT_EAN = "0000000000001";
    private static final String INCORRECT_EAN = "XX789WROMG";
    private static final String CATEGORY_JUST_FOR_SOLVING_TESTING_LATE_NIGHT_WANT_TO_GO_TO_BED_PLEASE_MAMA_MIA_LET_MI_GOOOOOOOO = "Food";


    private Assortment assortment;
    private Item testItem;
    private Map<String, Item> items;

    @BeforeEach
    void setUp() {
        testItem = new Item("Pineapple", 20000L, PINEAPPLE_EAN, CATEGORY_JUST_FOR_SOLVING_TESTING_LATE_NIGHT_WANT_TO_GO_TO_BED_PLEASE_MAMA_MIA_LET_MI_GOOOOOOOO);
        items = new HashMap<>();
        items.put(testItem.getEAN(), testItem);
        assortment = new Assortment(items);
    }

    @Test
    void testSize() {
        assertEquals(1, assortment.getNumberOfItemsInAssortment());
    }

    @Test
    void testContainsItem() {
        assertTrue(assortment.contains(PINEAPPLE_EAN), "assortment does not contain specified argument");
    }

    @Test
    void testNotContainsItem() {
        assertFalse(assortment.contains(NON_PRESENT_EAN), "assortment contains argument it should not contain");
    }

    @Test
    void testGetItem() {
        Optional<Item> result = assortment.getItem(PINEAPPLE_EAN);
        assertTrue(result.isPresent());
        assertEquals(testItem, result.get());
    }

    @Test
    void testGetItemNotPresent() {
        Optional<Item> result = assortment.getItem(NON_PRESENT_EAN);
        assertFalse(result.isPresent());
    }

    @Test
    void testGetItemWithNullEAN() {
        assertThrows(NullPointerException.class, () -> assortment.getItem(null));
    }

    @Test
    void testGetItemWithIncorrectEAN() {
        assertThrows(IllegalArgumentException.class, () -> assortment.getItem(INCORRECT_EAN));
    }
}