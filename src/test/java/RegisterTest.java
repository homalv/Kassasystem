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
    private Register registerWithMockedCart;
    private Scanner scanner;

    @BeforeEach
    void setUp() {
        scanner = mock(Scanner.class);

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
        when(scanner.getEAN()).thenReturn(PINEAPPLE_EAN);

        assertTrue(registerWithInitPurchase.addToCart(scanner.getEAN()));
        // Testing to be done ONLY by ShoppingCartTest????
        assertEquals(1, registerWithInitPurchase.getCart().numbOfItems());
    }

    @Test
    void testDoesNotAddItemToCartIfEANNotInAssortment() {
        when(scanner.getEAN()).thenReturn(NON_PRESENT_EAN);

        assertFalse(registerWithInitPurchase.addToCart(scanner.getEAN()));
        // Testing to be done ONLY by ShoppingCartTest????
        assertEquals(0, registerWithInitPurchase.getCart().numbOfItems());
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



    // proceedToPayment (probably not in Register but i UI)

    // chosePaymentMethod(CreditCard || GiftCard)

    // pay ==> Card.collectFunds()

    // IF PAYED THEN printReceipt()

    // abortPurchase

    // IF PAYED THEN logg(completedPurchase) --> Store as Purchase

    // getRefund(purchaseId, itemToRefund, chosenRefundPaymentEntity) --> retrievePurchase(purchaseId)
    // chosenRefundPaymentEntity.refund(long money)

}
