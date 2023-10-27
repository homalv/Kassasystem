import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class AssortmentFactory {

    // GPT suggestions for handling static methods
    private static CSVLoader csvLoader = new CSVLoader(Path.of("dummy.csv"));
    private static CSVParser csvParser = new CSVParser();

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

    // GPT suggestions for handling static methods
    // Helper method for test cases to replace the real CSVLoader with a mock
    static void replaceCSVLoader(CSVLoader mockLoader) {
        csvLoader = mockLoader;
    }

    // GPT suggestions for handling static methods
    // Helper method for test cases to replace the real CSVParser with a mock
    static void replaceCSVParser(CSVParser mockParser) {
        csvParser = mockParser;
    }
}