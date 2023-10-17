public interface ReceiptRepository {

    boolean add(Receipt receipt);

    Receipt get(int serialNumber);

    // getAll()?

}
