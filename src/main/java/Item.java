import java.util.Objects;

public class Item {

    private final long price;
    private final String name;
    private static final int HIGHEST_ISO88591_CHAR_VALUE = 255;
    private static final int NAME_MAX_LENGTH = 30;

    public Item(String name, long price) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
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

        this.name = trimmedName;
        this.price = price;
    }

    private boolean containsNonISO88591(String name) {
        return name.chars().anyMatch(c -> c > HIGHEST_ISO88591_CHAR_VALUE);
    }

    private String trim(String name) {
        String trimmedName = name.trim();

        // Regex: Delete all whitespaces between words when there are more than one
        trimmedName = trimmedName.replaceAll("\\s{2,}", " ");

        return trimmedName;
    }

    public long getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return price == item.price &&
                name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, name);
    }
}
