import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssortmentTest {

    private static final String ASSORTMENT_RESOURCE_PATH = "/test-assortment.csv";
    private static final String NON_CSV_FILE_PATH = "/items.txt";
    private static final String INVALID_PATH = "/invalid/invalid.csv";
    private static final String NON_NUMERICAL_STRING_IN_CSV_PATH = "/non-numerical-item-string.csv";
    private static final String OUT_OF_BOUNDS_CSV_PATH = "/oob.csv";
    private static final long PINEAPPLE_EAN = 1234567890017L;
    private final Item pineapple = new Item("Pineapple",200);

    @Test
    void ctrThrowsForNonCsvFile() {
        assertThrows(IllegalArgumentException.class, () -> new Assortment(NON_CSV_FILE_PATH));
    }

    @Test
    void ctrThrowsForInvalidResourcePath() {
        assertThrows(NullPointerException.class, () -> new Assortment(INVALID_PATH));
    }

    @Test
    void ctrThrowsForNonNumericalString() {
        assertThrows(NumberFormatException.class, () -> new Assortment(NON_NUMERICAL_STRING_IN_CSV_PATH));
    }

    @Test
    void ctrThrowsForInvalidCSVFormat() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> new Assortment(OUT_OF_BOUNDS_CSV_PATH));
    }

    @Test
    void ctrPopulatesAssortment() {
        Assortment assortment = new Assortment(ASSORTMENT_RESOURCE_PATH);
        assertEquals(50, assortment.getAssortmentSize());
    }

    @Test
    void testAssortmentContainsItem() {
        Assortment assortment = new Assortment(ASSORTMENT_RESOURCE_PATH);
        assertTrue(assortment.contains(PINEAPPLE_EAN));
    }
}