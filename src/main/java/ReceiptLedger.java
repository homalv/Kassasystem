public class ReceiptLedger implements ReceiptRepository {

    @Override
    public boolean add(Receipt receiptToLog) {
        return true;
    }

    @Override
    public Receipt get(int serialNumber) {
        return null;
    }
}
