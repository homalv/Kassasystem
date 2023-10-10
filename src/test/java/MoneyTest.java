import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    // Minor Unit --> Constant
    @Test
    void testMoneyHasCorrectValue() {
        Money money = new Money(1);
        assertEquals(1, money.getValue());
    }

    // Tests negative values
    @Test
    void testValueCannotBeNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Money(-1));
    }

    // Tests negative values (with Long.MAX_VALUE) -> Same result as test above
    @Test
    void testValueCannotExceedLongMAX_VALUE() {
        assertThrows(IllegalArgumentException.class, () -> new Money(Long.MAX_VALUE + 1));
    }


}