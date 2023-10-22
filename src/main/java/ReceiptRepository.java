import java.util.ArrayList;

public interface ReceiptRepository {

    boolean add(Receipt receipt);

    Receipt get(int serialNumber);

    ArrayList<Receipt> getAllReceipts();

}
