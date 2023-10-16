import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ReceiptTest {
    private ArrayList<LineItem> lineOfItems;



    @Test
    void checkValidSerialNumber(){
        Receipt receipt = new Receipt(lineOfItems);
        String serialNumber = receipt.getSerialNumber();
        assertTrue(Long.parseLong(serialNumber) > 0L);
    }
    
    // @Test  //Detta test räknar samtliga instanser av receipt har därför sitt fjärde test här. Måste lösa detta.
    // void checkFormatOfSerialNumber_DateAndSerial(){
    //     Receipt receipt = new Receipt(lineOfItems);
    //     String currentDay = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
    //     assertEquals(currentDay+"00001", receipt.getSerialNumber());
    // }

    @Test
    void checkLocalTimeOnReceipt() {
        Receipt receipt = new Receipt(lineOfItems);
        LocalTime localTime = LocalTime.now();
        Duration difference = Duration.between(localTime, receipt.getReceiptTime());
        long seconds = Math.abs(difference.get(ChronoUnit.SECONDS));
        assertTrue(seconds < 2);
    }

    @Test
    void checkLocalDateOnReceipt() {
        Receipt receipt = new Receipt(lineOfItems);
        LocalDate currentDay = LocalDate.now();
        assertEquals(currentDay,receipt.getReceiptDate());
    }

    @Test
    void testReceiptHasLineItems() {
        Receipt receipt = new Receipt(new ArrayList<>());
        assertNotNull(receipt.getLineItems());
    }

// Ta emot en samling LineItems
// Räkna antal varor
// Räkna totalsumma
// Räkna Moms


}
