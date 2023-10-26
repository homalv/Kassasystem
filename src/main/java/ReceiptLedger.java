import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReceiptLedger implements ReceiptRepository {
    private Set<Receipt> receipts = new HashSet<>();

    @Override
    public boolean add(Receipt receiptToLog) {
        receipts.add(receiptToLog);
        return true;
    }
}
