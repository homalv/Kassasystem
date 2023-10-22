import java.util.Map;
import java.util.Optional;

public class Assortment {

    private final Map<String, Item> items;

    public Assortment(Map<String, Item> items) {
        this.items = items;

    }

    public int getNumberOfItemsInAssortment() {
        return items.size();
    }

    public boolean contains(String ean) {
        return items.containsKey(ean);
    }

    public Optional<Item> getItem(String ean) {
        if (ean == null) {
            throw new NullPointerException("EAN should not be null");
        }

        if (isInvalidEanFormat(ean)) {
            throw new IllegalArgumentException("EAN must have 13 numerical characters");
        }

        return Optional.ofNullable(items.get(ean));
    }

    public boolean isInvalidEanFormat(String str) {
        return !(str.length() == 13 && str.matches("^\\d+$"));
    }
}
