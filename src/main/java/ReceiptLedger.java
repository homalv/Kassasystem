import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReceiptLedger implements ReceiptRepository {
    private Set<Receipt> receipts = new HashSet<>();

    public ReceiptLedger() {

    }

    @Override
    public boolean add(Receipt receiptToLog) {
        if(receipts.contains(receiptToLog) || receiptToLog == null) {
            return false;
        }
        receipts.add(receiptToLog);
        return true;
    }

    @Override
    public Receipt get(int serialNumber) {
        return null;
    }

    @Override
    public ArrayList<Receipt> getAllReceipts() {
        return null;
    }
}
