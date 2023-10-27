import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Generated using ChatGPT 4.
 * Prompt used:
 * CSVParser class and previous CSVParserTest class
 * "Write tests for 100% statement coverage"
 * ************************************************
 * Result:
 * 100% statement coverage is achieved
 * */
class CSVParserTest {

    @Test
    void testParseWithValidCSVLines() {
        // Arrange
        CSVParser csvParser = new CSVParser();
        List<String> validCSVLines = List.of(
                "1234567890010,Sandwich,Food,8000",
                "9876543210010,Drink,Food,500"
        );

        // Act
        Map<String, Item> itemsMap = csvParser.parse(validCSVLines);

        // Assert
        assertEquals(2, itemsMap.size());
        assertTrue(itemsMap.containsKey("1234567890010"));
        assertTrue(itemsMap.containsKey("9876543210010"));
    }

    @Test
    void testParseWithInvalidCSVLineFormat() {
        // Arrange
        CSVParser csvParser = new CSVParser();
        List<String> invalidCSVLines = List.of(
                "1234567890010,Sandwich,Food,8000",
                "9876543210010,Drink,Beverage", // Missing Price
                "1234" // Incomplete line
        );

        // Act and Assert
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> csvParser.parse(invalidCSVLines));
    }

    @Test
    void testParseWithInvalidPriceFormat() {
        // Arrange
        CSVParser csvParser = new CSVParser();
        List<String> invalidPriceCSVLines = List.of(
                "1234567890010,Sandwich,Food,8000",
                "9876543210010,Drink,Beverage,InvalidPrice"
        );

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> csvParser.parse(invalidPriceCSVLines));
    }
}
