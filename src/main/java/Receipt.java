import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Receipt {
    private int serialNumberCounter = 0;
    private final String serialNumberString;
    private final LocalDate receiptDay;
    private final LocalTime receiptTime;
    private final List<LineItem> lineItems;

    /*kvittot har ett serienummer som baseras på dagens datum + fem siffror till.
    Om det är en ny dag så nollställs de sista fem. Exempel 23100500001 */
    public Receipt(List<LineItem> listOfItems){
        updateReceiptDayAndCounter();
        serialNumberCounter ++;
        int serialNumber = serialNumberCounter;
        this.receiptDay = LocalDate.now();
        this.receiptTime = LocalTime.now();
        lineItems = listOfItems;

        String datePrefix = receiptDay.format(DateTimeFormatter.ofPattern("yyMMdd"));
        serialNumberString = datePrefix + String.format("%05d", serialNumber);

    }

    private  void updateReceiptDayAndCounter() {
        if (receiptDay == null || LocalDate.now().isAfter(receiptDay)) {
            serialNumberCounter = 0;
        }
    }

    public String getSerialNumber(){
        return serialNumberString;
    }

    public List<LineItem> getLineItems(){
        return lineItems;
    }

    public LocalDate getReceiptDate(){
        return receiptDay;
    }

    public LocalTime getReceiptTime(){
        return receiptTime;
    }
}
