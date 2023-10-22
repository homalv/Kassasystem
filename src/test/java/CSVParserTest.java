import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

class CSVParserTest {

    private static final Path ASSORTMENT_RESOURCE_PATH = Path.of("test-assortment.csv");

    public List<String> getLoadedCSVLines(Path pathToCsv) throws IOException {
        return new CSVLoader().load(pathToCsv);
    }

    @Test
    void testParsesWithoutErrors() {

    }


/*    @Test
    void ctrThrowsForNonNumericalString() {
        assertThrows(NumberFormatException.class, () -> new Assortment(NON_NUMERICAL_STRING_IN_CSV_PATH));
    }

    @Test
    void ctrThrowsForInvalidCSVFormat() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> new Assortment(OUT_OF_BOUNDS_CSV_PATH));
    }*/

    /*
     * EAN Code,Item Name,Category,Price
     * 1234567890010,Sandwich,Food,8000
     * */

    /*
     * production-assortment == test-assortment
     * */

    // Test with empty file
    // items.txt

    // Test with wrong file type

    // Test each col?

    // Test col in line in CSV is out of bounds
    // oob.csv

    // Test price string is non-numerical
    // non-numerical-item-string.csv

    // Exception e = assertThrows()
    // + Check message

    // String s = assertTimeout()
    // + additional checks on s


}