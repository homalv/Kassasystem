import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class AssortmentFactory {

    public static Assortment createAssortment(Path csvResourcePath) {
        CSVLoader loader = new CSVLoader(csvResourcePath);
        List<String> csvLines;

        try {
            csvLines = loader.loadLinesFromCsvPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CSVParser parser = new CSVParser();
        Map<String, Item> itemsMap = parser.parse(csvLines);

        return new Assortment(itemsMap);
    }
}