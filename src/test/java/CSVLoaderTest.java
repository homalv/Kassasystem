import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;


class CSVLoaderTest {

    private static final Path REAL_PATH = Path.of("assortment.csv");
    private static final Path NON_CSV_FILE_PATH = Path.of("items.txt");
    private static final Path INVALID_PATH = Path.of("invalid/invalid.csv");
    private static final Path EMPTY_CSV_PATH = Path.of("empty.csv");
    private static final Path NO_HEADER_CSV_PATH = Path.of("empty-header.csv");
    private static final Path MISSING_HEADER = Path.of("empty-header.csv");
    private static final Path ABOVE_100_LINES = Path.of("above-one-hundred-lines.csv");

    private final CSVLoader csvLoader = new CSVLoader(REAL_PATH);

    @Test
    void loadThrowsForNullPath() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new CSVLoader(null));
        assertEquals(e.getMessage(), "Path is null");
    }

    @Test
    void loadThrowsForNonCsvFile() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new CSVLoader(NON_CSV_FILE_PATH));
        assertEquals(e.getMessage(), "Provided resource path is not a CSV file: " + NON_CSV_FILE_PATH);
    }

/*    @Test
    void loadThrowsForInvalidResourcePath() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new CSVLoader(INVALID_PATH));
        assertEquals("File does not exist: " + INVALID_PATH, e.getMessage());
    }*/

    @Test
    void loadThrowsForMissingHeader() {
        CSVLoader csvLoader = new CSVLoader(NO_HEADER_CSV_PATH);
        IOException e = assertThrows(IOException.class, csvLoader::loadLinesFromCsvPath);
        assertEquals("File is missing header", e.getMessage());
    }

    @Test
    void loadThrowsForFileMissingHeader() {
        CSVLoader csvLoader = new CSVLoader(MISSING_HEADER);
        IOException e = assertThrows(IOException.class, csvLoader::loadLinesFromCsvPath);
        assertEquals("File is missing header", e.getMessage());
    }

    @Test
    void loadThrowsForEmptyCsvFile() {
        CSVLoader csvLoader = new CSVLoader(EMPTY_CSV_PATH);
        NullPointerException e = assertThrows(NullPointerException.class, csvLoader::loadLinesFromCsvPath);
        assertEquals("Header is empty", e.getMessage());
    }

    @Test
    void loadThrowsForExceedingMaxLineCount() {
        CSVLoader csvLoader = new CSVLoader(ABOVE_100_LINES);
        IOException e = assertThrows(IOException.class, csvLoader::loadLinesFromCsvPath);
        assertEquals("Exceeded maximum line count of 100", e.getMessage());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "test-assortment.csv", numLinesToSkip = 1)
    void testCsvFileLines(String eanCode,  String itemName, String Category, String Price) {
    }

/*    @ParameterizedTest
    @ValueSource(strings = {
            "src/test/resources/assortment/empty.csv",
            "src/test/resources/assortment/empty-header.csv",
            "src/test/resources/assortment/above-one-hundred-lines.csv"
    })
    void loadThrowsForVariousCsvFiles(String fileName) {
        Path path = Path.of(fileName);
        CSVLoader loader = new CSVLoader(path);
        IOException e = assertThrows(IOException.class, loader::loadLinesFromCsvPath);

        // Dynamic check based on the filename
        switch (fileName) {
            case "src/test/resources/assortment/empty.csv":
                assertEquals("File is empty", e.getMessage());
                break;
            case "empty-header.csv":
                assertEquals("File is missing header", e.getMessage());
                break;
            case "above-one-hundred-lines.csv":
                assertEquals("Exceeded maximum line count of 100", e.getMessage());
                break;
            default:
                fail("Unhandled test case for file: " + fileName);
        }
    }*/

    @ParameterizedTest
    @ValueSource(strings = {
            "src/test/resources/assortment/empty.csv",
            "src/test/resources/assortment/empty-header.csv",
            "src/test/resources/assortment/above-one-hundred-lines.csv"
    })
    void loadThrowsForVariousCsvFiles(String fileName) {
        Path path = Path.of(fileName);
        CSVLoader loader = new CSVLoader(path);
        IOException e = assertThrows(IOException.class, loader::loadLinesFromCsvPath);

        // Adjust the checks for specific exception messages
        if (fileName.equals("empty.csv")) {
            assertEquals("File is empty", e.getMessage());
        } else if (fileName.equals("empty-header.csv")) {
            assertEquals("File is missing header", e.getMessage());
        } else if (fileName.equals("above-one-hundred-lines.csv")) {
            assertEquals("Exceeded maximum line count of 100", e.getMessage());
        }
    }

    @Test
    void testWindowsPath() {
        assumeTrue(System.getProperty("os.name").startsWith("Windows"));
    }

    @Test
    void testMacPath() {
        assumeTrue(System.getProperty("os.name").startsWith("Mac"));
    }

    @Test
    void testLinuxPath() {
        assumeTrue(System.getProperty("os.name").startsWith("Linux"));
    }
}
