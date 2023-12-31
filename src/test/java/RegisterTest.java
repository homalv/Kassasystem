import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


class RegisterTest {

    private static final Path ASSORTMENT_RESOURCE_PATH = Path.of("test-assortment.csv");
    private static final String PINEAPPLE_EAN = "1234567890017";
    private static final String NON_PRESENT_EAN = "1000000000001";
    private Register register;
    private Register registerWithInitPurchase;
    private Scanner scanner;
    private ReceiptLedger ledger;

    @BeforeEach
    void setUp() {
        scanner = mock(Scanner.class);
        when(scanner.initialize()).thenReturn(true);

        ledger = new ReceiptLedger();

        Assortment assortment = AssortmentFactory.createAssortment(ASSORTMENT_RESOURCE_PATH);
        register = new Register(assortment, ledger, scanner);

        Assortment assortment2 = AssortmentFactory.createAssortment(ASSORTMENT_RESOURCE_PATH);
        registerWithInitPurchase = new Register(assortment2, ledger, scanner);
        registerWithInitPurchase.initializePurchase();
    }

    // SETUP METHOD
    public Register getRegisterWithCartWithOneAddedItem() {
        Assortment assortment2 = AssortmentFactory.createAssortment(ASSORTMENT_RESOURCE_PATH);
        Register registerWithInitPurchase = new Register(assortment2, ledger, scanner);
        registerWithInitPurchase.initializePurchase();
        registerWithInitPurchase.addToCart(PINEAPPLE_EAN);
        return registerWithInitPurchase;
    }

    // Register instance initialization
    @Test
    void testCreatesRegister() {
        assertNotNull(register);
    }

    @Test
    void testShoppingCartNotInitializedUponCreation() {
        assertNull(register.getCart());
    }

    @Test
    void testInitializePurchaseAssignsNewShoppingCart() {
        register.initializePurchase();
        assertNotNull(register.getCart());
    }

    @Test
    void testInitializePurchaseInstantiatesCart() {
        assertNull(register.getCart());
        register.initializePurchase();
        assertNotNull(register.getCart());
    }

    // Tests for addToCart()
    @Test
    void testAddToNullCartThrows() {
        assertThrows(IllegalStateException.class, () -> register.addToCart(PINEAPPLE_EAN));
    }

    @Test
    void testAddsItemToCartIfEANInAssortment() {
        when(scanner.getEAN()).thenReturn(PINEAPPLE_EAN);

        assertTrue(registerWithInitPurchase.addToCart(scanner.getEAN()));
        // Testing to be done ONLY by ShoppingCartTest????
        assertEquals(1, registerWithInitPurchase.getCart().numOfItemsInShoppingCart());
    }

    @Test
    void testDoesNotAddItemToCartIfEANNotInAssortment() {
        when(scanner.getEAN()).thenReturn(NON_PRESENT_EAN);

        assertFalse(registerWithInitPurchase.addToCart(scanner.getEAN()));
        // Testing to be done ONLY by ShoppingCartTest????
        assertEquals(0, registerWithInitPurchase.getCart().numOfItemsInShoppingCart());
    }

    @Test
    void testCannotAddIfScanningCompleted() {
        Register register = getRegisterWithCartWithOneAddedItem();
        register.setScanningCompleted(true);
        assertThrows(IllegalStateException.class, () -> register.addToCart(PINEAPPLE_EAN));
    }

    // Tests for removeFromCart()
    @Test
    void testRemoveFromNullCartThrows() {
        assertThrows(IllegalStateException.class, () -> register.removeFromCart(PINEAPPLE_EAN));
    }


    @Test
    void testRemoveItemExistingInCart() {
        when(scanner.getEAN()).thenReturn(PINEAPPLE_EAN);
        registerWithInitPurchase.addToCart(scanner.getEAN());

        assertTrue(registerWithInitPurchase.removeFromCart(PINEAPPLE_EAN));
    }

    @Test
    void testRemoveItemNotExistingInCart() {
        when(scanner.getEAN()).thenReturn(PINEAPPLE_EAN);
        registerWithInitPurchase.addToCart(scanner.getEAN());

        when(scanner.getEAN()).thenReturn(NON_PRESENT_EAN);
        assertFalse(registerWithInitPurchase.removeFromCart(NON_PRESENT_EAN));
    }

    @Test
    void testCannotRemoveIfScanningCompleted() {
        Register register = getRegisterWithCartWithOneAddedItem();
        register.setScanningCompleted(true);
        assertThrows(IllegalStateException.class, () -> register.removeFromCart(PINEAPPLE_EAN));
    }

    // Test for cancelPurchase()
    @Test
    void testCancelPurchaseSetCartToNull() {
        Register register = getRegisterWithCartWithOneAddedItem();
        register.cancelPurchase();
        assertNull(register.getCart());
    }

    // Scanning
    @Test
    void testScanningCompleteTrue() {
        Register register = getRegisterWithCartWithOneAddedItem();
        register.setScanningCompleted(true);
        assertTrue(register.getScanningCompleted());
    }

    @Test
    void testScanningCompleteFalse() {
        assertFalse(getRegisterWithCartWithOneAddedItem().getScanningCompleted());
    }

    // Payment
    @Test
    void testPurchaseIsPaid() {
        Register register = getRegisterWithCartWithOneAddedItem();
        register.setScanningCompleted(true);
        register.pay();
        assertTrue(register.getCart().isPaid());
    }

    // Logging
    @Test
    void testPaidPurchaseReceiptIsLogged() {
        Register register = getRegisterWithCartWithOneAddedItem();
        register.setScanningCompleted(true);
        register.pay();
        assertTrue(register.logReceipt(register.createReceipt()));
    }

    // ScanToAdd
    @Test
    void testCannotScanToAddIfScannerNotConnected() {
        when(scanner.isConnected()).thenReturn(false);
        assertFalse(register.scanToAdd());
    }

    @Test
    void testCanAddToCartWhenScannerIsConnectedAndReturnEAN() {
        when(scanner.isConnected()).thenReturn(true);
        when(scanner.getEAN()).thenReturn(PINEAPPLE_EAN);
        register.initializePurchase();
        assertTrue(register.scanToAdd());
    }


    // Refund

    // getRefund(purchaseId, itemToRefund, chosenRefundPaymentEntity) --> retrievePurchase(purchaseId)

    // chosenRefundPaymentEntity.refund(long money)



    // Print -> Register.print() --> MockPrinter

    // IF PAYED THEN printReceipt()

    // IF PAYED THEN logg(completedPurchase)

}
