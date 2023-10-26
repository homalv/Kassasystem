import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.nio.file.Path;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ReceiptLedgerTest {
    private static final Path ASSORTMENT_RESOURCE_PATH = Path.of("test-assortment.csv");
    private static final String PINEAPPLE_EAN = "1234567890017";
    private Register registerWithInitPurchase;
    @Mock
    private ReceiptLedger receiptLedger;
    private Receipt receipt;

    @BeforeEach
    void setUp() {
        Assortment assortment = AssortmentFactory.createAssortment(ASSORTMENT_RESOURCE_PATH);
        registerWithInitPurchase = new Register(assortment, receiptLedger);
        MockitoAnnotations.initMocks(this);
        registerWithInitPurchase.initializePurchase();
        receipt = getReceiptWithOneItem();
    }

    public Receipt getReceiptWithOneItem() {
        registerWithInitPurchase.initializePurchase();
        registerWithInitPurchase.addToCart(PINEAPPLE_EAN);
        return registerWithInitPurchase.createReceipt();
    }

    @Test
    void testAddReceipt() {
        when(receiptLedger.add(any(Receipt.class))).thenReturn(true);
        boolean result = receiptLedger.add(receipt);
        assertTrue(result);
    }

    @Test
    void testAddDuplicateReceipt() {
        when(receiptLedger.add(any(Receipt.class))).thenReturn(false);
        boolean result = receiptLedger.add(receipt);
        assertFalse(result);
    }

}