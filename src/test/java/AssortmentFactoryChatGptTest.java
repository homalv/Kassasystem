import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssortmentFactoryChatGptTest {

    @Test
    void testCreateAssortmentWithValidCSVFile() throws IOException {
        // Arrange
        Path csvResourcePath = Path.of("test-assortment.csv");

        // Mock the behavior of CSVLoader
        CSVLoader loader = mock(CSVLoader.class);
        when(loader.loadLinesFromCsvPath()).thenReturn(List.of("1234567890010,Sandwich,Food,8000"));

        // Mock the behavior of CSVParser
        CSVParser parser = mock(CSVParser.class);
        when(parser.parse(anyList())).thenReturn(Map.of("1234567890010", new Item("Sandwich", 8000, "1234567890010", "Food")));

        // Replace the real CSVLoader and CSVParser with the mocks
        AssortmentFactory.replaceCSVLoader(loader);
        AssortmentFactory.replaceCSVParser(parser);

        // Act
        Assortment assortment = AssortmentFactory.createAssortment(csvResourcePath);

        // Assert
        assertNotNull(assortment);
    }

    @Test
    void testCreateAssortmentWithIOException() throws IOException {
        // Arrange
        Path csvResourcePath = Path.of("non-existent-file.csv");

        // Mock the behavior of CSVLoader to throw an IOException
        CSVLoader loader = mock(CSVLoader.class);
        when(loader.loadLinesFromCsvPath()).thenThrow(new IOException("File not found"));

        // Replace the real CSVLoader with the mock
        AssortmentFactory.replaceCSVLoader(loader);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> AssortmentFactory.createAssortment(csvResourcePath));
    }
}
