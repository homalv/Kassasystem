public class EAN {
    private String EANNumber = "";
    private static final String DEFAULT_COUNTRY_PREFIX = "012";
    private static final String DEFAULT_MANUFACTURER_ID = "34567";
    private static final String ERROR_MESSAGE_INVALID_CHAR = "Only digits are accepted, 0-9";

    public EAN(String countryPrefix, String manufacturerDigits, String productDigits) {
        this.EANNumber += isCountryPrefixValid(countryPrefix);
        this.EANNumber += isManufacturerDigitsValid(manufacturerDigits);
        this.EANNumber += isProductDigitsValid(productDigits);
        this.EANNumber += calculateCheckDigit();
    }

    public EAN(String productDigitsOrCompleteEANNumber) {
        if (productDigitsOrCompleteEANNumber.equals(null)) {
            throw new NullPointerException();
        }
        for (char c : productDigitsOrCompleteEANNumber.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_CHAR);
            }
        }

        if (productDigitsOrCompleteEANNumber.length() == 4)

        {
            createEANFromProductDigits(productDigitsOrCompleteEANNumber);
            return;
        }
        if (productDigitsOrCompleteEANNumber.length() == 13) {
            createEANFromCompleteEANNumber(productDigitsOrCompleteEANNumber);
            return;
        }
        if (productDigitsOrCompleteEANNumber.length() == 12){
            createEANFromEanNumberWithoutCheckDigit(productDigitsOrCompleteEANNumber);
            return;
        }

        throw new IllegalArgumentException();

    }

    private void createEANFromEanNumberWithoutCheckDigit(String EANNumberExcludingCheckDigit) {
        this.EANNumber = EANNumberExcludingCheckDigit;
        this.EANNumber += calculateCheckDigit();
    }

    private void createEANFromCompleteEANNumber(String completeEAN) {
        this.EANNumber = completeEAN;
    }

    private void createEANFromProductDigits(String productDigits) {
        this.EANNumber += DEFAULT_COUNTRY_PREFIX;
        this.EANNumber += DEFAULT_MANUFACTURER_ID;
        this.EANNumber += productDigits;
        this.EANNumber += calculateCheckDigit();
    }

    public String getEANNumber() {
        return EANNumber;
    }

    private int calculateCheckDigit() {
        char[] EANArray = EANNumber.toCharArray();
        int checkDigitSum = 0;
        for (int i = 0; i < EANArray.length; i++) {
            int digit = Character.getNumericValue(EANArray[i]);
            if ((i) % 2 == 0) {
                checkDigitSum += digit * 3;
            } else {
                checkDigitSum += digit;
            }
        }
        return 10 - (checkDigitSum % 10);
    }

    private String isCountryPrefixValid(String countryPrefix) {
        if (countryPrefix.length() != 3) {
            throw new IllegalArgumentException("Country prefix needs to be 3 digits");
        }
        if (countryPrefix.equals(null)) {
            throw new NullPointerException("Country prefix can't be null");
        }
        for (char c : countryPrefix.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_CHAR);
            }
        }
        return countryPrefix;

    }

    private String isManufacturerDigitsValid(String manufacturerDigits) {
        if (manufacturerDigits.length() != 5) {
            throw new IllegalArgumentException("Manufacturer digits needs to be 5 digits");
        }
        if (manufacturerDigits.equals(null)) {
            throw new NullPointerException("Manufacturer digits can't be null");
        }
        for (char c : manufacturerDigits.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_CHAR);
            }
        }
        return manufacturerDigits;
    }

    private String isProductDigitsValid(String productDigits) {
        if (productDigits.length() != 4) {
            throw new IllegalArgumentException("Manufacturer digits needs to be 4 digits");
        }
        if (productDigits.equals(null)) {
            throw new NullPointerException("Manufacturer digits can't be null");
        }
        for (char c : productDigits.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_CHAR);
            }
        }
        return productDigits;
    }
}
