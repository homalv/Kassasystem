import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReceiptTest {
    private ArrayList<LineItem> lineOfItems;



    @Test
    void checkValidSerialNumber(){
        Receipt receipt = new Receipt(lineOfItems);
        String serialNumber = receipt.getSerialNumber();
        assertTrue(Long.parseLong(serialNumber) > 0L);
    }

      /* OBS! Detta test räknar samtliga instanser av receipt och funkar
    bara om "expected" är lika med antalet instanser under testet */
      @Test
      void checkFormatOfSerialNumber_DateAndSerial(){
         Receipt receipt = new Receipt(lineOfItems);
         String currentDay = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
         assertEquals(currentDay+"00006", receipt.getSerialNumber());
     }

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

    @Test
    void testGetSameListOfLineItems() {
        Item mockedItem = mock(Item.class);
        when(mockedItem.getEAN()).thenReturn("EAN");
        List<LineItem> lineItems = new ArrayList<>();
        lineItems.add(new LineItem(mockedItem,10));
        Receipt receipt = new Receipt(lineItems);
        assertEquals(lineItems, receipt.getLineItems());
    }




}
