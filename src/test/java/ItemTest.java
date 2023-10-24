import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    private static final String ITEM_NAME = "AN ITEM00!";
    private static final String NON_ISO_88591_NAME = "Дмитkрий";
    private static final long PRICE = 1000;
    private static final long NEGATIVE_PRICE = -1;
    private static final String EMPTY_STRING = "";
    private static final String BLANK_STRING = " ";
    private static final int ACCEPTED_NAME_LENGTH = 30;
    private static final String TOO_LONG_NAME = " THISSTRINGCONTAINSOVERTHIRTYCHARS ";
    private static final String ITEM_NAME_WITH_WHITESPACE = "  AN                                    ITEM00!  ";
    private static final String TOO_LONG_EAN = "111111111111111";
    private static final String TOO_SHORT_EAN = "11111111111";
    private static final String EAN_WITH_NON_DIGITS = "1111ea@";
    private static final String VALID_EAN = "1234567890000";
    private static final String VALID_SECOND_EAN = "1234567890102";

    private static final String CATEGORY = "Food";
    private Item correctItemObject;

    @BeforeEach
    void setUp() {
        correctItemObject = new Item(ITEM_NAME, PRICE, VALID_EAN, CATEGORY);
    }

    // P01, N01
    @Test
    void testCreatesItemWithCorrectNamePriceEANAndCategory() {
        Item i = new Item(ITEM_NAME, PRICE, VALID_EAN, CATEGORY);
        assertEquals(1000, i.getPrice());
        assertEquals(ITEM_NAME, i.getName());
        assertEquals(VALID_EAN, i.getEAN());
        assertEquals(CATEGORY, i.getCategory());
    }

    // P02
    @Test
    void testPriceNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> new Item(ITEM_NAME, NEGATIVE_PRICE, VALID_EAN, CATEGORY));
    }

    // N02
    @Test
    void testNameContainsNonISOCharsThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Item(NON_ISO_88591_NAME, PRICE, VALID_EAN, CATEGORY));
    }

    // N03
    @Test
    void testNameStringIsNullThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Item(null, PRICE, VALID_EAN, CATEGORY));
    }

    // N04, N05
    @Test
    void testNameStringIsEmptyOrBlankThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Item(EMPTY_STRING, PRICE, VALID_EAN, CATEGORY));
        assertThrows(IllegalArgumentException.class, () -> new Item(BLANK_STRING, PRICE, VALID_EAN, CATEGORY));
    }

    // N07
    @Test
    void testTrimmedNameLengthOutsideRangeThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Item(TOO_LONG_NAME, PRICE, VALID_EAN, CATEGORY));
    }

    // N06
    @Test
    void testTooLongWithWSTrimmedToWithinRange() {
        Item i = new Item(ITEM_NAME_WITH_WHITESPACE, PRICE, VALID_EAN, CATEGORY);
        assertEquals(ITEM_NAME, i.getName());
        assertTrue(i.getName().length() < ACCEPTED_NAME_LENGTH);
    }

    @Test
    void testEqualsSameValues() {
        assertEquals(new Item(ITEM_NAME, PRICE, VALID_EAN, CATEGORY), correctItemObject);
    }

    @Test
    void testEqualsFalseSameClass() {
        assertNotEquals(new Item("B", 1, VALID_SECOND_EAN, CATEGORY), correctItemObject);
    }

    @Test
    void testEqualsDifferentClasses() {
        assertNotEquals(correctItemObject, new Object());
    }

    @Test
    void testEqualsFalseForNull() {
        assertNotEquals(correctItemObject, null);
    }

    @Test
    public void testEqualsSymmetricReflexiveTransitive() {
        Item x = new Item(ITEM_NAME, PRICE, VALID_EAN, CATEGORY);
        Item y = new Item(ITEM_NAME, PRICE, VALID_EAN, CATEGORY);
        assertTrue(x.equals(y) && y.equals(x));
        assertEquals(x.hashCode(), y.hashCode());
    }

    @Test
    void testEANWithTooShortEAN() { // countryDigits01
        assertThrows(IllegalArgumentException.class,
                () -> new Item(ITEM_NAME, PRICE, TOO_SHORT_EAN, CATEGORY));

    }

    @Test
    void testEANWithTooLongEAN() { // countryDigits01
        assertThrows(IllegalArgumentException.class,
                () -> new Item(ITEM_NAME, PRICE, TOO_LONG_EAN, CATEGORY));

    }

    @Test
    void testEANWithInvalidChars() { // countryDigits05
        assertThrows(IllegalArgumentException.class,
                () -> new Item(ITEM_NAME, PRICE, EAN_WITH_NON_DIGITS, CATEGORY));
    }

    @Test
    void testNullEAN() {
        assertThrows(IllegalArgumentException.class, () -> new Item(ITEM_NAME, PRICE, null, CATEGORY));
    }
    @Test
    void testGetVatThrowsIfCategoryDoesNotExist(){
        assertThrows(IllegalArgumentException.class, () -> new Item(ITEM_NAME, 112, VALID_EAN, "sadad"));
    }
    @Test
    void testGetVatGivesCorrectValueForFoodItems() {
        Item testItem = new Item(ITEM_NAME, 112, VALID_EAN, CATEGORY);
        long testItemVat = testItem.getVAT();
        assertEquals(12, testItemVat);
        
    }

    @Test
    void testGetVatGivesCorrectValueForLiteratureItems() {
        Item testItem = new Item(ITEM_NAME, 106, VALID_EAN, "Literature");
        long testItemVat = testItem.getVAT();
        assertEquals(6, testItemVat);
        
    }

    @Test
    void testGetVatGivesCorrectValueForOfficeSuppliesItems() {
        Item testItem = new Item(ITEM_NAME, 125, VALID_EAN, "Office Supplies");
        long testItemVat = testItem.getVAT();
        assertEquals(25, testItemVat);
        
    }

}