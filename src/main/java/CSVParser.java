import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO maybe this is a generic Parser class and
//  by some class design I should have another class dedicated to parsing csvs to collections of items?
public class CSVParser {

    // TODO If the CSVParser should be reused this should be modifiable
    private static final int REQUIRED_CSV_PER_LINE = 4;


    // TODO Equivalence Partitions for the method
    public Map<String, Item> parse(List<String> csvLines) {
        Map<String, Item> itemsMap = new HashMap<>();

        for (String line : csvLines) {
            String[] parts = line.split(",");

            if (parts.length != REQUIRED_CSV_PER_LINE) {
                throw new ArrayIndexOutOfBoundsException("CSV line format is incorrect. Ensure each line has the correct number of fields");
            }

            // test if the parts match the criteria of
            String itemEan = parts[0];
            String itemName = parts[1];
            String itemCategory = parts[2];
            long itemPrice;
            try {
                itemPrice = Long.parseLong(parts[3]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid price format for item in CSV line: " + line, e);
            }

            // TODO if any of the ctr arguments are invalid input - an exception will be thrown in the Item ctr
            //  - how can I handle this? Should this method stop? Null cannot be placed in Map right?
            //  Do I skip the item or restart? --> Probably throw here and ask caller to call parse() again.

            Item item = new Item(itemName, itemPrice, itemEan, itemCategory);
            itemsMap.put(itemEan, item);
        }

        return itemsMap;
    }
}

