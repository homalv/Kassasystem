import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Receipt {
    private static int serialNumberCounter = 0;
    private int serialNumber;
    private String serialNumberString;
    private static LocalDate receiptDay;
    private final LocalTime receiptTime;
    private ArrayList<LineItem> lineItems;

    /*kvittot har ett serienummer som baseras på dagens datum + fem siffror till.
    Om det är en ny dag så nollställs de sista fem. Exempel 23100500001 */
    public Receipt(ArrayList<LineItem> listOfItems){
        updateReceiptDayAndCounter();
        serialNumberCounter ++;
        this.serialNumber = serialNumberCounter;
        this.receiptDay = LocalDate.now();
        this.receiptTime = LocalTime.now();
        lineItems = listOfItems;

        String datePrefix = receiptDay.format(DateTimeFormatter.ofPattern("yyMMdd"));
        serialNumberString = datePrefix + String.format("%05d",serialNumber);

    }

    private static void updateReceiptDayAndCounter() {
        if (receiptDay == null || LocalDate.now().isAfter(receiptDay)) {
            serialNumberCounter = 0;
        }
    }

    public String getSerialNumber(){
        return serialNumberString;
    }

    public ArrayList<LineItem> getLineItems(){
        return lineItems;
    }

    public LocalDate getReceiptDate(){
        return receiptDay;
    }

    public LocalTime getReceiptTime(){
        return receiptTime;
    }
}
