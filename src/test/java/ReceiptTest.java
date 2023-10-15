import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ReceiptTest {



    @Test
    void checkValidSerialNumber(){
        Receipt receipt = new Receipt();
        String serialNumber = receipt.getSerialNumber();
        assertTrue(Long.parseLong(serialNumber) > 0L);
    }
    @Test
    void checkFormatOfSerialNumber_DateAndSerial(){
        Receipt receipt = new Receipt();
        String currentDay = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        assertEquals(currentDay+"00004", receipt.getSerialNumber());
    }

    @Test
    void checkLocalTimeOnReceipt() {
        Receipt receipt = new Receipt();
        LocalTime localTime = LocalTime.now();
        Duration difference = Duration.between(localTime, receipt.getReceiptTime());
        long seconds = Math.abs(difference.get(ChronoUnit.SECONDS));
        assertTrue(seconds < 2);
    }

    @Test
    void checkLocalDateOnReceipt() {
        Receipt receipt = new Receipt();
        LocalDate currentDay = LocalDate.now();
        assertEquals(currentDay,receipt.getReceiptDate());
    }

// Ta emot en samling LineItems
// Räkna antal varor
// Räkna totalsumma
// Räkna Moms


}
