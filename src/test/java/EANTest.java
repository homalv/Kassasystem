import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EANTest {
    private static final String VALID_COUNTRY_PREFIX = "012";
    private static final String VALID_MANUFACTURER_DIGITS = "34567";
    private static final String VALID_PRODUCT_DIGITS = "8900";
    private static final String VALID_COMPLETE_EAN = "0123456789005";
    private static final String VALID_EAN_WITHOUT_CHECKDIGIT = "012345678900";
    private static final String INVALID_COUNTRY_PREFIX_TOO_SHORT = "01";
    private static final String INVALID_COUNTRY_PREFIX_TOO_LONG = "01";
    private static final String INVALID_COUNTRY_PREFIX_INVALID_CHARS = "a12";
    private static final String INVALID_MANUFACTURER_DIGITS_TOO_SHORT = "3456";
    private static final String INVALID_MANUFACTURER_DIGITS_TOO_LONG = "345677";
    private static final String INVALID_MANUFACTURER_DIGITS_INVALID_CHARS = "a4567";
    private static final String INVALID_PRODUCT_DIGITS_TOO_SHORT = "890";
    private static final String INVALID_PRODUCT_DIGITS_TOO_LONG = "89000";
    private static final String INVALID_PRODUCT_DIGITS_INVALID_CHARS = "a900";
    private static final String NULL_STRING = null;
    private static final String INVALID_FIVE_DIGITS = "12345";
    private static final int INVALID_INTEGER = 123;
    private static final String INVALID_THIRTEEN_DIGITS_EAN = "01234567890005";

    @Test
    void testEANWithCorrectValues() { // Test01
        EAN testEAN = new EAN(VALID_COUNTRY_PREFIX, VALID_MANUFACTURER_DIGITS, VALID_PRODUCT_DIGITS);
        assertEquals(VALID_COMPLETE_EAN, testEAN.getEANNumber());
    }

    @Test
    void testEANWithOnlyProductDigits() { // Test17
        EAN testEAN = new EAN(VALID_PRODUCT_DIGITS);
        assertEquals(VALID_COMPLETE_EAN, testEAN.getEANNumber());
    }

    @Test
    void testEANWithCompleteEAN() { // Test19
        EAN testEAN = new EAN(VALID_COMPLETE_EAN);
        assertEquals(VALID_COMPLETE_EAN, testEAN.getEANNumber());
    }

    @Test
    void testEANWithMissingCheckDigit() { // Test18
        EAN testEAN = new EAN(VALID_EAN_WITHOUT_CHECKDIGIT);
        assertEquals(VALID_COMPLETE_EAN, testEAN.getEANNumber());
    }

    @Test
    void testEANWithTooShortCountryPrefix() { // Test02
        assertThrows(IllegalArgumentException.class,
                () -> new EAN(INVALID_COUNTRY_PREFIX_TOO_SHORT, VALID_MANUFACTURER_DIGITS, VALID_PRODUCT_DIGITS));

    }

    @Test
    void testEANWithTooLongCountryPrefix() { // Test03
        assertThrows(IllegalArgumentException.class,
                () -> new EAN(INVALID_COUNTRY_PREFIX_TOO_LONG, VALID_MANUFACTURER_DIGITS, VALID_PRODUCT_DIGITS));
    }

    @Test
    void testEANwithNullCountryPrefix() { // Test04
        assertThrows(NullPointerException.class,
                () -> new EAN(NULL_STRING, VALID_MANUFACTURER_DIGITS, VALID_PRODUCT_DIGITS));

    }

    @Test
    void testEANWithInvalidCharsCountryPrefix() { // Test06
        assertThrows(IllegalArgumentException.class,
                () -> new EAN(INVALID_COUNTRY_PREFIX_INVALID_CHARS, VALID_MANUFACTURER_DIGITS, VALID_PRODUCT_DIGITS));
    }

    @Test
    void testEANWithTooShortManufacturerDigits() { // Test07
        assertThrows(IllegalArgumentException.class,
                () -> new EAN(VALID_COUNTRY_PREFIX, INVALID_MANUFACTURER_DIGITS_TOO_SHORT, VALID_PRODUCT_DIGITS));
    }

    @Test
    void testEANWithTooLongManufacturerDigits() { // Test08
        assertThrows(IllegalArgumentException.class,
                () -> new EAN(VALID_COUNTRY_PREFIX, INVALID_MANUFACTURER_DIGITS_TOO_LONG, VALID_PRODUCT_DIGITS));
    }

    @Test
    void testEANwithNullManufacturerDigits() { // Test09
        assertThrows(NullPointerException.class,
                () -> new EAN(VALID_COUNTRY_PREFIX, NULL_STRING, VALID_PRODUCT_DIGITS));
    }

    @Test
    void testEANWithInvalidCharsInManufacturerDigits() { // test11
        assertThrows(IllegalArgumentException.class,
                () -> new EAN(VALID_COUNTRY_PREFIX, INVALID_MANUFACTURER_DIGITS_INVALID_CHARS, VALID_PRODUCT_DIGITS));
    }

    @Test
    void testEANWithTooShortProductDigits() { // Test12
        assertThrows(IllegalArgumentException.class,
                () -> new EAN(VALID_COUNTRY_PREFIX, VALID_MANUFACTURER_DIGITS, INVALID_PRODUCT_DIGITS_TOO_SHORT));
    }

    @Test
    void testEANWithTooLongProductDigits() { // Test13
        assertThrows(IllegalArgumentException.class,
                () -> new EAN(VALID_COUNTRY_PREFIX, VALID_MANUFACTURER_DIGITS, INVALID_PRODUCT_DIGITS_TOO_LONG));
    }

    @Test
    void testEANwithNullProductDigits() { // Test14
        assertThrows(NullPointerException.class,
                () -> new EAN(VALID_COUNTRY_PREFIX, VALID_MANUFACTURER_DIGITS, NULL_STRING));

    }

    @Test
    void testEANWithInvalidCharsInProductDigits() { // Test16
        assertThrows(IllegalArgumentException.class,
                () -> new EAN(VALID_COUNTRY_PREFIX, VALID_MANUFACTURER_DIGITS, INVALID_PRODUCT_DIGITS_INVALID_CHARS));
    }

    @Test
    void testEANWithOnlyProductDigitsThatsTooShort() { // Test20
        assertThrows(IllegalArgumentException.class,
                () -> new EAN(INVALID_PRODUCT_DIGITS_TOO_SHORT));

    }

    @Test
    void testEANWithFiveDigits() { // Test21
        assertThrows(IllegalArgumentException.class,
                () -> new EAN(INVALID_FIVE_DIGITS));

    }

    @Test
    void testEANWithMoreThenThirteenDigits() { // Test22
        assertThrows(IllegalArgumentException.class,
                () -> new EAN(INVALID_THIRTEEN_DIGITS_EAN));
    }

    @Test
    void testEANOneParameterConstructorWithInvalidChars() { // TEST23
        assertThrows(IllegalArgumentException.class,
                () -> new EAN(INVALID_PRODUCT_DIGITS_INVALID_CHARS));
    }

    @Test
    void testEANOneparameterConstructorWithNull() { // Test25
        assertThrows(NullPointerException.class,
                () -> new EAN(NULL_STRING));
    }
}
