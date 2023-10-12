public class EAN {
    private String EANNumber = "";
    private String DEFAULT_COUNTRY_PREFIX = "012";
    private String DEFAULT_MANUFACTURER_ID = "34567";
    
    public EAN(String countryPrefix, String manufacturerDigits, String productDigits){
        isCountryPrefixValid(countryPrefix);
        isManufacturerDigitsValid(manufacturerDigits);
        isProductDigitsValid(productDigits);
        this.EANNumber += countryPrefix;
        this.EANNumber += manufacturerDigits;
        this.EANNumber += productDigits;
    }
    public EAN(String productDigits){
        this.EANNumber += DEFAULT_COUNTRY_PREFIX;
        this.EANNumber += DEFAULT_MANUFACTURER_ID; 
        this.EANNumber += productDigits;
    }
    public String getEANNumber() {
        return EANNumber;
    }
    private void isCountryPrefixValid(String countryPrefix){
        if (countryPrefix.length() != 3){
            throw new IllegalArgumentException("Country prefix needs to be 3 digits");
        }
        if (countryPrefix.equals(null)){
            throw new NullPointerException("Country prefix can't be null");
        }
        for (char c : countryPrefix.toCharArray()){
            if(!Character.isDigit(c)){
                throw new IllegalArgumentException("Only digits are accepted, 0-9");
            }
        }
    
    }
     private void isManufacturerDigitsValid(String manufacturerDigits){
        if (manufacturerDigits.length() != 5){
            throw new IllegalArgumentException("Manufacturer digits needs to be 5 digits");
        }
        if (manufacturerDigits.equals(null)){
            throw new NullPointerException("Manufacturer digits can't be null");
        }
        for (char c : manufacturerDigits.toCharArray()){
            if(!Character.isDigit(c)){
                throw new IllegalArgumentException("Only digits are accepted, 0-9");
            }
        }
    }

     private void isProductDigitsValid(String productDigits){
        if (productDigits.length() != 4){
            throw new IllegalArgumentException("Manufacturer digits needs to be 4 digits");
        }
        if (productDigits.equals(null)){
            throw new NullPointerException("Manufacturer digits can't be null");
        }
        for (char c : productDigits.toCharArray()){
            if(!Character.isDigit(c)){
                throw new IllegalArgumentException("Only digits are accepted, 0-9");
            }
        }
    }
}
