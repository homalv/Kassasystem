import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SuiteDisplayName;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;

@SuiteDisplayName("Assortment Test Suite")
class AssortmentTest {

    // TODO --> USE JUnit CSV
    // TODO --> Use Path
    // TODO --> CTR faster

    private static final int CTR_EXE_TIME_THRESHOLD = 0;
    private static final String ASSORTMENT_RESOURCE_PATH = "/test-assortment.csv";
    private static final String NON_CSV_FILE_PATH = "/items.txt";
    private static final String INVALID_PATH = "/invalid/invalid.csv";
    private static final String NON_NUMERICAL_STRING_IN_CSV_PATH = "/non-numerical-item-string.csv";
    private static final String OUT_OF_BOUNDS_CSV_PATH = "/oob.csv";
    private static final String PINEAPPLE_EAN = "1234567890017";
    private final Item pineapple = new Item("Pineapple",200);

    @Test
    @Disabled("Test for displaying the disabled annotation")
    void skippedTest() {
        // Empty
    }

    @Test
    @DisplayName("Ctr execution speed test")
    void testCtrSpeedBelowThreshold() {
        assertTimeout(ofMillis(CTR_EXE_TIME_THRESHOLD), () -> {
            Assortment assortment = new Assortment(ASSORTMENT_RESOURCE_PATH);
        }, "Ctr executing exceeded accepted threshold");
    }

    @Test
    @DisplayName("Ctr exe speed test - abort preemptively")
    void testCtrSpeedBelowThresholdAbortPreemptively() {
        assertTimeoutPreemptively(ofMillis(CTR_EXE_TIME_THRESHOLD), () -> {
            Assortment assortment = new Assortment(ASSORTMENT_RESOURCE_PATH);
        }, "Ctr executing exceeded accepted threshold - abort exe preemptively");
    }


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
        assertEquals(37, assortment.getAssortmentSize(), "Num of lines csv does not match expectations");
    }

    @Test
    void testAssortmentContainsItem() {
        Assortment assortment = new Assortment(ASSORTMENT_RESOURCE_PATH);
        assertTrue(assortment.contains(PINEAPPLE_EAN), "Assortment does not contain specified argument");
    }
}