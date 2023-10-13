import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EANTest {
    private final static String VALID_COUNTRY_PREFIX = "012";
    private final static String VALID_MANUFACTURER_DIGITS = "34567";
    private final static String VALID_PRODUCT_DIGITS = "8900";
    private final static String VALID_COMPLETE_EAN = "0123456789005";
    private final static String INVALID_COUNTRY_PREFIX_TOO_SHORT = "01";
    private final static String INVALID_COUNTRY_PREFIX_TOO_LONG = "01";
    private final static String INVALID_COUNTRY_PREFIX_INVALID_CHARS = "a12";
    private final static String INVALID_MANUFACTURER_DIGITS_TOO_SHORT = "3456";
    private final static String INVALID_MANUFACTURER_DIGITS_TOO_LONG = "345677";
    private final static String INVALID_MANUFACTURER_DIGITS_INVALID_CHARS = "a4567";
    private final static String INVALID_PRODUCT_DIGITS_TOO_SHORT = "890";
    private final static String INVALID_PRODUCT_DIGITS_TOO_LONG = "89000";
    private final static String INVALID_PRODUCT_DIGITS_INVALID_CHARS = "a900";
    private final static String NULL_STRING = null;

    @Test
    void testEANWithCorrectValues() { // countryDigits06, manfucturerDigits06, productDigits06
        EAN testEAN = new EAN(VALID_COUNTRY_PREFIX, VALID_MANUFACTURER_DIGITS, VALID_PRODUCT_DIGITS);
        assertEquals(VALID_COMPLETE_EAN, testEAN.getEANNumber());
    }
    @Test
    void testEANWithOnlyProductDigits() {
        EAN testEAN = new EAN(VALID_PRODUCT_DIGITS);
        assertEquals(VALID_COMPLETE_EAN, testEAN.getEANNumber());
    }

    @Test
    void testEANWithTooShortCountryPrefix() { // countryDigits01
        assertThrows(IllegalArgumentException.class,
                () -> new EAN(INVALID_COUNTRY_PREFIX_TOO_SHORT, VALID_MANUFACTURER_DIGITS, VALID_PRODUCT_DIGITS));

    }

    @Test
    void testEANWithTooLongCountryPrefix() { // countryDigits02
        assertThrows(IllegalArgumentException.class,
                () -> new EAN(INVALID_COUNTRY_PREFIX_TOO_LONG, VALID_MANUFACTURER_DIGITS, VALID_PRODUCT_DIGITS));
    }

    @Test
    void testEANwithNullCountryPrefix() { // countryDigits03
        assertThrows(NullPointerException.class,
                () -> new EAN(null, VALID_MANUFACTURER_DIGITS, VALID_PRODUCT_DIGITS));

    }

    @Test
    void testEANWithInvalidCharsCountryPrefix() { // countryDigits05
        assertThrows(IllegalArgumentException.class,
                () -> new EAN(INVALID_COUNTRY_PREFIX_INVALID_CHARS, VALID_MANUFACTURER_DIGITS, VALID_PRODUCT_DIGITS));
    }

    @Test
    void testEANWithTooShortManufacturerDigits() { // manufacturerDigits01
        assertThrows(IllegalArgumentException.class,
                () -> new EAN(VALID_COUNTRY_PREFIX, INVALID_MANUFACTURER_DIGITS_TOO_SHORT, VALID_PRODUCT_DIGITS));
    }

    @Test
    void testEANWithTooLongManufacturerDigits() { // manufacturerDigits02
        assertThrows(IllegalArgumentException.class,
                () -> new EAN(VALID_COUNTRY_PREFIX, INVALID_MANUFACTURER_DIGITS_TOO_LONG, VALID_PRODUCT_DIGITS));
    }

    @Test
    void testEANwithNullManufacturerDigits() { // manufacturerDigits03
        assertThrows(NullPointerException.class,
                () -> new EAN(VALID_COUNTRY_PREFIX, NULL_STRING, VALID_PRODUCT_DIGITS));
    }

    @Test
    void testEANWithInvalidCharsInManufacturerDigits() { // manufacturerDigits05
        assertThrows(IllegalArgumentException.class,
                () -> new EAN(VALID_COUNTRY_PREFIX, INVALID_MANUFACTURER_DIGITS_INVALID_CHARS, VALID_PRODUCT_DIGITS));
    }

    @Test
    void testEANWithTooShortProductDigits() { // productDigits01
        assertThrows(IllegalArgumentException.class,
                () -> new EAN(VALID_COUNTRY_PREFIX, VALID_MANUFACTURER_DIGITS, INVALID_PRODUCT_DIGITS_TOO_SHORT));
    }

    @Test
    void testEANWithTooLongProductDigits() { // productDigits02
        assertThrows(IllegalArgumentException.class,
                () -> new EAN(VALID_COUNTRY_PREFIX, VALID_MANUFACTURER_DIGITS, INVALID_PRODUCT_DIGITS_TOO_LONG));
    }

    @Test
    void testEANwithNullProductDigits() { // productDigits03
        assertThrows(NullPointerException.class,
                () -> new EAN(VALID_COUNTRY_PREFIX, VALID_MANUFACTURER_DIGITS, NULL_STRING));

    }

    @Test
    void testEANWithInvalidCharsInProductDigits() { // productDigits05
        assertThrows(IllegalArgumentException.class,
                () -> new EAN(VALID_COUNTRY_PREFIX, VALID_MANUFACTURER_DIGITS, INVALID_PRODUCT_DIGITS_INVALID_CHARS));
    }
}
