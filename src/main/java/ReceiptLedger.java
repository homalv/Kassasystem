import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReceiptLedger implements ReceiptRepository {
    private List<Receipt> receipts = new ArrayList<>();
    // should use the real file instead of temp, but i have no access atm. TODO
    private static String RECEIPT_LEDGER_RESOURCE_PATH = System.getProperty("java.io.tmpdir") + "/receipt-ledger.txt";

    public ReceiptLedger() {

    }

    @Override
    public boolean add(Receipt receiptToLog) {
        try {
            FileWriter writer = new FileWriter(RECEIPT_LEDGER_RESOURCE_PATH, true);
            String serializedReceipt = serializeReceipt(receiptToLog);
            writer.write(serializedReceipt);
            writer.write(System.lineSeparator());
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private String serializeReceipt(Receipt receipt) {
        return receipt.getSerialNumber();
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
