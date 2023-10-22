import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class CSVLoaderTest {

    // TODO check tests again
    private static final Path NON_CSV_FILE_PATH = Path.of("items.txt");
    private static final Path INVALID_PATH = Path.of("invalid/invalid.csv");
    private static final Path EMPTY_CSV_PATH = Path.of("/empty.csv");
    private static final Path NO_HEADER_CSV_PATH = Path.of("emptyheader.csv");

    private final CSVLoader csvLoader = new CSVLoader();

    @Test
    void loadThrowsForNullPath() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> csvLoader.load(null));
        assertEquals(e.getMessage(), "Path is null");
    }

    @Test
    void loadThrowsForNonCsvFile() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> csvLoader.load(NON_CSV_FILE_PATH));
        assertEquals(e.getMessage(), "Provided resource path is not a CSV file: " + NON_CSV_FILE_PATH);
    }

    @Test
    void loadThrowsForInvalidResourcePath() {
        IOException e = assertThrows(IOException.class, () -> csvLoader.load(INVALID_PATH));
        assertEquals(e.getMessage(), "Resource not found: " + INVALID_PATH);
    }

    @Test
    void loadThrowsForMissingHeader() {
        IOException e = assertThrows(IOException.class, () -> csvLoader.load(NO_HEADER_CSV_PATH));
        assertEquals(e.getMessage(), "File is missing header");
    }

    // Test Windows path --> assumeTrue(System.getenv("OS").startsWith("Windows"))

    // Test Mac path

}
