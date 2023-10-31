import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RegisterStateMachineTest {

    private static final String AN_ITEM_EAN = "1234567890010";

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
        when(scanner.getEAN()).thenReturn(AN_ITEM_EAN);
        scanner.initialize();

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

    @Test
    void testPurchaseStatesOfStateMachineForRegisterWithSpy() throws IOException {
        Register realRegister = registerInstanceSetup();
        Register register = spy(realRegister);

        when(scanner.getEAN()).thenReturn(AN_ITEM_EAN);
        scanner.initialize();

        // CI
        register.initializePurchase();
        verify(register).initializePurchase();

        // IS, IA
        register.addToCart(scanner.getEAN());
        verify(register).addToCart(scanner.getEAN());

        // IS, IR
        register.removeFromCart(scanner.getEAN());
        verify(register).removeFromCart(scanner.getEAN());

        // IS, IA
        register.addToCart(scanner.getEAN());
        verify(register, times(2)).addToCart(scanner.getEAN());  // Verifiera att addToCart anropas 2 gånger

        // SC
        register.setScanningCompleted(true);
        verify(register).setScanningCompleted(true);

        // PA
        register.pay();
        verify(register).pay();


        Receipt receiptToLog = register.createReceipt();
        verify(register).createReceipt();

        // RL
        register.logReceipt(receiptToLog);
        verify(register).logReceipt(receiptToLog);

        // ID
        register.cancelPurchase();
        assertNull(register.getCart());
    }
}
