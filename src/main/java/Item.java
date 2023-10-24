import java.util.Objects;

public class Item {

    private static final int HIGHEST_ISO88591_CHAR_VALUE = 255;
    private static final int NAME_MAX_LENGTH = 30;
    private static final int THIRTEEN = 13;
    private static final String REGEX = "\\s{2,}";
    private final long price;
    private final Category category;
    private final String name;
    private final EAN EANNumber;


    public Item(String name, long price, String EANNumber, String category) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (EANNumber == null){
            throw new IllegalArgumentException("The EAN number cannot be null");
        }

        if (name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty or blank");
        }

        if (containsNonISO88591(name)) {
            throw new IllegalArgumentException("Contains non-ISO-8859-1");
        }

        String trimmedName = trim(name);
        if (trimmedName.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException("Trimmed string too long");
        }

        if (price < 0) {
            throw new IllegalArgumentException("Price must not be negative or above Long.MAX_VALUE");

        }
        if (EANNumber.length() != THIRTEEN) {
            throw new IllegalArgumentException("EAN needs to be 13 digits");
        }
        for (char c : EANNumber.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("Only digits are accepted in EAN, 0-9");
            }
        }

        this.name = trimmedName;
        this.price = price;
        this.EANNumber = new EAN(EANNumber);
        this.category = setCategory(category);
    }

    public String getEAN() {
        return EANNumber.getEANNumber();
    }

    public String getCategory() {
        return category.getCategory();
    }

    private Category setCategory(String category) {
        switch (category) {
            case "Food":
                return Category.FOOD;
            case "Literature":
                return Category.LITERATURE;
            case "Office Supplies":
                return Category.OFFICE_SUPPLIES;
            default:
            throw new IllegalArgumentException("Category got to be either Food, Literature or Office Supplies.");
        }

    }

    // Test through CTR
    private boolean containsNonISO88591(String name) {
        return name.chars().anyMatch(c -> c > HIGHEST_ISO88591_CHAR_VALUE);
    }

    // Test through CTR
    private String trim(String name) {
        String trimmedName = name.trim();

        // Regex: Delete all whitespaces between words when there are more than one
        trimmedName = trimmedName.replaceAll(REGEX, " ");

        return trimmedName;
    }

    public long getPrice() {
        return price;
    }

    public long getVAT() {
        switch (category) {
            case FOOD:
                return VAT.REDUCED.getVAT(price);
            case LITERATURE:
                return VAT.LOW.getVAT(price);
            case OFFICE_SUPPLIES:
                return VAT.STANDARD.getVAT(price);
            default:
                throw new IllegalArgumentException("There exists no VAT for this category");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;}
        if (o == null || getClass() != o.getClass()) {
            return false;}
        Item item = (Item) o;
        return Objects.equals(EANNumber.getEANNumber(), item.EANNumber.getEANNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(EANNumber.getEANNumber());
    }

}
