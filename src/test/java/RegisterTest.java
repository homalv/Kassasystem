import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


class RegisterTest {

    private static final String ASSORTMENT_RESOURCE_PATH = "/production-assortment.csv";
    private static final long PINEAPPLE_EAN = 1234567890017L;
    private static final long NON_PRESENT_EAN = 1000000000001L;
    private Register register;
    private Register registerWithInitPurchase;

    @BeforeEach
    void setUp() {
        Assortment assortment = new Assortment(ASSORTMENT_RESOURCE_PATH);
        register = new Register(assortment);
        Assortment assortment2 = new Assortment(ASSORTMENT_RESOURCE_PATH);
        registerWithInitPurchase = new Register(assortment2);
        registerWithInitPurchase.initializePurchase();
    }

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

    @Test
    void testAddsItemToCartIfEANInAssortment() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.getEAN()).thenReturn(PINEAPPLE_EAN);

        assertTrue(registerWithInitPurchase.addToCart(scanner.getEAN()));
        assertEquals(1, registerWithInitPurchase.getCart().size());
    }

    @Test
    void testDoesNotAddItemToCartIfEANNotInAssortment() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.getEAN()).thenReturn(NON_PRESENT_EAN);

        assertFalse(registerWithInitPurchase.addToCart(scanner.getEAN()));
        assertEquals(0, registerWithInitPurchase.getCart().size());
    }

    // scanToRemoveItem --> cart.remove()

    // proceedToPayment (probably not in Register but i UI)

    // chosePaymentMethod(CreditCard || GiftCard)

    // pay ==> Card.collectFunds()

    // IF PAYED THEN printReceipt()

    // abortPurchase

    // IF PAYED THEN logg(completedPurchase) --> Store as Purchase

    // getRefund(purchaseId, itemToRefund, chosenRefundPaymentEntity) --> retrievePurchase(purchaseId)
    // chosenRefundPaymentEntity.refund(long money)

}
