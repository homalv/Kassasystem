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
    private static final String TOO_LONG_EAN = "1111111111111";
    private static final String TOO_SHORT_EAN = "11111111111";
    private static final String EAN_WITH_NON_DIGITS = "1111ea@";
    private Item correctItemObject;

    @BeforeEach
    void setUp() {
        correctItemObject = new Item(ITEM_NAME, PRICE);
    }

    // P01, N01
    @Test
    void testCreatesItemWithCorrectNameAndPrice() {
        Item i = new Item(ITEM_NAME, PRICE);
        assertEquals(1000, i.getPrice());
        assertEquals(ITEM_NAME, i.getName());
    }

    // P02
    @Test
    void testPriceNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> new Item(ITEM_NAME, NEGATIVE_PRICE));
    }

    // N02
    @Test
    void testNameContainsNonISOCharsThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Item(NON_ISO_88591_NAME, PRICE));
    }

    // N03
    @Test
    void testNameStringIsNullThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Item(null, PRICE));
    }

    // N04, N05
    @Test
    void testNameStringIsEmptyOrBlankThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Item(EMPTY_STRING, PRICE));
        assertThrows(IllegalArgumentException.class, () -> new Item(BLANK_STRING, PRICE));
    }

    // N07
    @Test
    void testTrimmedNameLengthOutsideRangeThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Item(TOO_LONG_NAME, PRICE));
    }

    // N06
    @Test
    void testTooLongWithWSTrimmedToWithinRange() {
        Item i = new Item(ITEM_NAME_WITH_WHITESPACE, PRICE);
        assertEquals(ITEM_NAME, i.getName());
        assertTrue(i.getName().length() < ACCEPTED_NAME_LENGTH);
    }

    @Test
    void testEqualsSameValues() {
        assertEquals(new Item(ITEM_NAME, PRICE), correctItemObject);
    }

    @Test
    void testEqualsFalseSameClass() {
        assertNotEquals(new Item("B", 1), correctItemObject);
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
        Item x = new Item(ITEM_NAME, PRICE);
        Item y = new Item(ITEM_NAME, PRICE);
        assertTrue(x.equals(y) && y.equals(x));
        assertEquals(x.hashCode(), y.hashCode());
    }

    @Test
    void testEANWithTooShortEAN() { // countryDigits01
        assertThrows(IllegalArgumentException.class,
                () -> new Item(ITEM_NAME, PRICE, TOO_SHORT_EAN));

    }

    @Test
    void testEANWithTooLongEAN() { // countryDigits01
        assertThrows(IllegalArgumentException.class,
                () -> new Item(ITEM_NAME, PRICE, TOO_LONG_EAN));

    }
    @Test
    void testEANWithInvalidChars() { // countryDigits05
        assertThrows(IllegalArgumentException.class,
                () -> new Item(ITEM_NAME, PRICE, EAN_WITH_NON_DIGITS));
    }
    @Test
    void testNullEAN() {
        assertThrows(IllegalArgumentException.class, () -> new Item(ITEM_NAME, PRICE, null));
    }

}