import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    // Minor Unit --> Constant
    @Test
    void testMoneyHasCorrectValue() {
        Money money = new Money(5);
        assertEquals(5, money.getValue());
    }

    // getAmount()


}