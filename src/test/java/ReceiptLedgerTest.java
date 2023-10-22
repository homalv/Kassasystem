import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

// TODO: Jag har kopierat mycket från Register här, kan vi undvika?

class ReceiptLedgerTest {

    private static final Path ASSORTMENT_RESOURCE_PATH = Path.of("/production-assortment.csv");
    private static final String PINEAPPLE_EAN = "1234567890017";
    private Register registerWithInitPurchase;
    private ReceiptLedger ledger;
    private Map<String, Item> items;

    @BeforeEach
    void setUp() throws IOException {
        ledger = new ReceiptLedger();
        CSVLoader loader = new CSVLoader();
        CSVParser parser = new CSVParser();
        items = parser.parse(loader.load(ASSORTMENT_RESOURCE_PATH));
        Assortment assortment = new Assortment(items);
        registerWithInitPurchase = new Register(assortment, ledger);
        registerWithInitPurchase.initializePurchase();
    }

    public Register getRegisterWithCartWithOneAddedItem() {
        Assortment assortment2 = new Assortment(items);
        Register registerWithInitPurchase = new Register(assortment2, ledger);
        registerWithInitPurchase.initializePurchase();
        registerWithInitPurchase.addToCart(PINEAPPLE_EAN);
        return registerWithInitPurchase;
    }

    @Test
    void addReceiptToLedger() {
       Receipt r = getRegisterWithCartWithOneAddedItem().createReceipt();
       assertTrue(ledger.add(r));
       //var allReceipts = ledger.getAllReceipts();
       //assertEquals(allReceipts.get(allReceipts.size()-1), r);
    }
}