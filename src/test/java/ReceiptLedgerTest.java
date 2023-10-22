import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

// TODO: Jag har kopierat mycket från Register här, kan vi undvika?

class ReceiptLedgerTest {

    private static final String ASSORTMENT_RESOURCE_PATH = "/production-assortment.csv";
    private static final String PINEAPPLE_EAN = "1234567890017";
    private Register registerWithInitPurchase;
    private ReceiptLedger ledger;

    @BeforeEach
    void setUp() {
        ledger = new ReceiptLedger();
        Assortment assortment = new Assortment(ASSORTMENT_RESOURCE_PATH);
        registerWithInitPurchase = new Register(assortment, ledger);
        registerWithInitPurchase.initializePurchase();
    }

    public Register getRegisterWithCartWithOneAddedItem() {
        Assortment assortment2 = new Assortment(ASSORTMENT_RESOURCE_PATH);
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