import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVParser {

    private static final int REQUIRED_CSV_PER_LINE = 4;

    public Map<String, Item> parse(List<String> csvLines) {
        Map<String, Item> itemsMap = new HashMap<>();

        for (String line : csvLines) {
            String[] parts = line.split(",");

            if (parts.length != REQUIRED_CSV_PER_LINE) {
                throw new ArrayIndexOutOfBoundsException("CSV line format is incorrect. Ensure each line has the correct number of fields");
            }

            String ean = parts[0];
            String itemName = parts[1];
            String category = parts[2]; // TODO needs to be implemented
            long price;
            try {
                price = Long.parseLong(parts[3]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid price format in CSV line: " + line, e);
            }

            Item item = new Item(itemName, price, ean);
            itemsMap.put(ean, item);
        }

        return itemsMap;
    }
}

