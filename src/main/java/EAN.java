public class EAN {
    private String EANNumber = "";
    private String DEFAULT_COUNTRY_PREFIX = "012";
    private String DEFAULT_MANUFACTURER_ID = "34567";

    public EAN(String countryPrefix, String manufacturerDigits, String productDigits) {
        this.EANNumber += isCountryPrefixValid(countryPrefix);
        this.EANNumber += isManufacturerDigitsValid(manufacturerDigits);
        this.EANNumber += isProductDigitsValid(productDigits);
        this.EANNumber += calculateCheckDigit();
    }

    public EAN(String productDigits) {
        this.EANNumber += DEFAULT_COUNTRY_PREFIX;
        this.EANNumber += DEFAULT_MANUFACTURER_ID;
        this.EANNumber += productDigits;
        this.EANNumber += calculateCheckDigit();
    }

    public EAN(Long completeEAN) {
        if (completeEAN.equals(null)) {
            throw new NullPointerException();
        }
        String completeEANString = Long.toString(completeEAN);
        if (completeEANString.length() != 13) {
            throw new IllegalArgumentException();
        }
        this.EANNumber = completeEANString;
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
                throw new IllegalArgumentException("Only digits are accepted, 0-9");
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
                throw new IllegalArgumentException("Only digits are accepted, 0-9");
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
                throw new IllegalArgumentException("Only digits are accepted, 0-9");
            }
        }
        return productDigits;
    }
}
