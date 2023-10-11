import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


class RegisterTest {


    private Register register;


    @BeforeEach
    void setUp() {
        Assortment assortment = new Assortment();
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
    void testScanItemAddsToCart() {
        register.initializePurchase();


    }


    // removeScannedItem


    // proceedToPayment


    // abortPurchase


}
