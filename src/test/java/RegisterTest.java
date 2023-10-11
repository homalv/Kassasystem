import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;


class RegisterTest {



    private Register register;
    private static final String ASSORTMENT_RESOURCE_PATH = "/production-assortment.csv";
    private static final long PINEAPPLE_EAN = 1234567890017L;


    @BeforeEach
    void setUp() {
        Assortment assortment = new Assortment(ASSORTMENT_RESOURCE_PATH);
        register = new Register(assortment);
    }


    // Collection for retrieving StoreItems
    // TODO -> Field for Scanner?
    @Test
    void testCreatesRegister() {
        assertNotNull(register);
    }


    @Test
    void testShoppingCartNotInitializedUponCreation() {
        assertNull(register.getCart());
    }


    // start
    @Test
    void testInitializePurchaseAssignsNewShoppingCart() {
        register.initializePurchase();
        assertNotNull(register.getCart());
    }


    // scanItem() { use ItemScanner to get EAN --> Fetch from Assortment --> i -> addToCart}
    @Test
    void testInitializePurchaseInstantiatesCart() {
        assertNull(register.getCart());
        register.initializePurchase();
        assertNotNull(register.getCart());
    }

    /*// TODO -> MOCK EAN
    @Test
    void testItemInAssortmentAddedToCart() {
        register.initializePurchase();
        // Mock for missing functionality of Scanner and EAN
        EAN mockEAN = mock(EAN.class);
        when(mockEAN.getValue()).thenReturn(PINEAPPLE_EAN);
        *//*register.addToCart(mockEAN.getValue());*//*
        assertFalse(register.getCart().isEmpty());
    }*/

    @Test
    void testAddsItemToCartIfEANInAssortment() {
        register.initializePurchase();
        assertTrue(register.addToCart(PINEAPPLE_EAN));
    }

    // removeScannedItem


    // proceedToPayment


    // abortPurchase


}
