import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Assortment {

    // Integer should be replaced by EAN???
    private Map<Long, Item> items;

    public Assortment(String csvResourcePath)  {
        if (!csvResourcePath.endsWith(".csv")) {
            throw new IllegalArgumentException("Provided resource path is not a CSV file: " + csvResourcePath);
        }

        items = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream(csvResourcePath),
                        "Resource not found: " + csvResourcePath)))) {

            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length != 3) {
                    throw new ArrayIndexOutOfBoundsException("CSV file format is incorrect. Ensure each line has the correct number of fields");
                }

                Long ean = Long.parseLong(parts[0]);
                Item item = new Item(parts[1], Long.parseLong(parts[2]));
                items.put(ean, item);
            }
        } catch (IOException e) {
            System.err.println("IOException caught: Error reading the CSV file: " + e.getMessage());
            throw new RuntimeException("IOException caught: Error reading the CSV file");
        } catch (NumberFormatException e) {
            System.err.println("Error parsing a number from the CSV file: " + e.getMessage());
            throw new NumberFormatException("Error parsing a number from the CSV file");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("CSV file format is incorrect. Ensure each line has the correct number of fields: " + e.getMessage());
            throw new ArrayIndexOutOfBoundsException("CSV file format is incorrect. Ensure each line has the correct number of fields");
        }

    }

    public int getAssortmentSize() {
        return items.size();
    }

    public boolean contains(long ean) {
        return items.containsKey(ean);
    }
}
