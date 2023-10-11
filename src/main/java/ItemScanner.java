import java.util.HashMap;

public class ItemScanner {
    private final HashMap<String, Item> items = new HashMap<>();

    public ItemScanner() {
        // TEMP SOLUTION (SHOULD PROBABLY BE MOCKED OR SOMETHING)
        items.put("1234567890123", new Item("Milk", 10000));
        items.put("9876543210987", new Item("Bread", 5000));
    }

    public Item findItem(String ean) {
        return items.get(ean);
    }

}
