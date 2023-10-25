import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

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

    private static Register registerInstanceSetup() throws IOException {
        CSVLoader csvLoader = new CSVLoader();
        CSVParser csvParser = new CSVParser();
        var items = csvLoader.load(Path.of("assortment.csv"));
        Assortment assortment = new Assortment(csvParser.parse(items));
        ReceiptLedger receiptLedger = new ReceiptLedger();
        return new Register(assortment, receiptLedger);
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
