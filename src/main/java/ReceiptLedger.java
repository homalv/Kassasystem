import java.util.ArrayList;

public class ReceiptLedger implements ReceiptRepository {

    @Override
    public boolean add(Receipt receiptToLog) {
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
