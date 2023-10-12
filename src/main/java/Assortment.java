import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Assortment {

    private static final int AMOUNT_OF_CSV_VALUES = 3;
    private final Map<Long, Item> items = new HashMap<>();

    public Assortment(String csvResourcePath)  {
        if (!csvResourcePath.endsWith(".csv")) {
            throw new IllegalArgumentException("Provided resource path is not a CSV file: " + csvResourcePath);
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream(csvResourcePath),
                        "Resource not found: " + csvResourcePath)))) {

            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length != AMOUNT_OF_CSV_VALUES) {
                    throw new ArrayIndexOutOfBoundsException("CSV file format is incorrect. Ensure each line has the correct number of fields");
                }

                Long ean = Long.parseLong(parts[0]);
                Item item = new Item(parts[1], Long.parseLong(parts[2]));
                items.put(ean, item);
            }
        } catch (IOException e) {
            throw new RuntimeException("IOException caught: Error reading the CSV file", e);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Error parsing a number from the CSV file");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("CSV file format is incorrect. Ensure each line has the correct number of fields");
        }
    }

    public int getAssortmentSize() {
        return items.size();
    }

    public boolean contains(long ean) {
        return items.containsKey(ean);
    }

    public Optional<Item> getItem(long ean) {
        return Optional.ofNullable(items.get(ean));
    }
}
