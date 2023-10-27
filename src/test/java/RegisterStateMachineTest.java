import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RegisterStateMachineTest {

    private static final String PINEAPPLE_EAN = "1234567890017";

    private Scanner scanner;

    @BeforeEach
    void setUp() {
        scanner = mock(Scanner.class);
    }

    private Register registerInstanceSetup() throws IOException {
        Path csvResourcePath = Path.of("assortment.csv");

        CSVLoader csvLoader = new CSVLoader(csvResourcePath);
        CSVParser csvParser = new CSVParser();

        List<String> csvLines = csvLoader.loadLinesFromCsvPath();
        Map<String, Item> items = csvParser.parse(csvLines);

        Assortment assortment = new Assortment(items);
        ReceiptLedger receiptLedger = new ReceiptLedger();

        scanner = mock(Scanner.class);

        return new Register(assortment, receiptLedger, scanner);
    }

    @Test
    void testRefundStatesOfStateMachineForRegister() {
        // TODO MOCK???
    }

    /**
     * Statement lines comments indicate covered state of state machine
     * */
    @Test
    void testPurchaseStatesOfStateMachineForRegister() throws IOException {
        Register register = registerInstanceSetup();
        when(scanner.getEAN()).thenReturn(PINEAPPLE_EAN);

        // CI
        register.initializePurchase();
        assertNotNull(register.getCart());

        // IS, IA
        assertTrue(register.addToCart(scanner.getEAN()));

        // IS, IR
        assertTrue(register.removeFromCart(scanner.getEAN()));

        // IS, IA
        assertTrue(register.addToCart(scanner.getEAN()));

        // SC
        register.setScanningCompleted(true);
        assertTrue(register.getScanningCompleted());

        // PA
        register.pay();
        assertTrue(register.getCart().isPaid());

        // RC --> TODO weak test?
        Receipt receiptToLog = register.createReceipt();
        assertNotNull(receiptToLog);

        // RL
        assertTrue(register.logReceipt(receiptToLog));

        // ID
        register.cancelPurchase();
        assertNull(register.getCart());
    }
}
