

public class EAN {
    private String EANNumber = "";
    private static final String DEFAULT_COUNTRY_PREFIX = "012";
    private static final String DEFAULT_MANUFACTURER_ID = "34567";
    private static final String ERROR_MESSAGE_INVALID_CHAR = "Only digits are accepted, 0-9";

    public EAN(String countryPrefix, String manufacturerDigits, String productDigits) {
        this.EANNumber += countryPrefixValidator(countryPrefix);
        this.EANNumber += manufacturerDigitsValidator(manufacturerDigits);
        this.EANNumber += productDigitsValidator(productDigits);
        this.EANNumber += calculateCheckDigit();
    }

    public EAN(String productDigitsOrCompleteEANNumber) {
        if(productDigitsOrCompleteEANNumber == null){
            throw new NullPointerException("EAN cannot be null");
        }
        for (char c : productDigitsOrCompleteEANNumber.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_CHAR);
            }
        }

        if (productDigitsOrCompleteEANNumber.length() == 4) {
            createEANFromProductDigits(productDigitsOrCompleteEANNumber);
            return;
        }
        if (productDigitsOrCompleteEANNumber.length() == 13) {
            createEANFromCompleteEANNumber(productDigitsOrCompleteEANNumber);
            return;
        }
        if (productDigitsOrCompleteEANNumber.length() == 12) {
            createEANFromEanNumberWithoutCheckDigit(productDigitsOrCompleteEANNumber);
            return;
        }

        throw new IllegalArgumentException(
                "EAN must be either complete, 12 digits excluding the checkdigit or a product code, 4 digits.");

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
            if ((i) % 2 == 1) {
                checkDigitSum += digit * 3;
            } else {
                checkDigitSum += digit;
            }
        }
        return 10 - (checkDigitSum % 10);
    }

    private String countryPrefixValidator(String countryPrefix) {
        if (countryPrefix == null) {
            throw new NullPointerException("Country prefix can't be null");
        }
        if (countryPrefix.length() != 3) {
            throw new IllegalArgumentException("Country prefix needs to be 3 digits");
        }

        for (char c : countryPrefix.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_CHAR);
            }
        }
        return countryPrefix;

    }

    private String manufacturerDigitsValidator(String manufacturerDigits) {
        if (manufacturerDigits == null) {
            throw new NullPointerException("Manufacturer digits can't be null");
        } if (manufacturerDigits.length() != 5) {
            throw new IllegalArgumentException("Manufacturer digits needs to be 5 digits");
        }

        for (char c : manufacturerDigits.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_CHAR);
            }
        }
        return manufacturerDigits;
    }

    private String productDigitsValidator(String productDigits) {
        if (productDigits == null) {
            throw new NullPointerException("Manufacturer digits can't be null");
        }
        if (productDigits.length() != 4) {
            throw new IllegalArgumentException("Manufacturer digits needs to be 4 digits");
        }

        for (char c : productDigits.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_CHAR);
            }
        }
        return productDigits;
    }
}
