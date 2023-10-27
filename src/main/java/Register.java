import java.util.Optional;

public class Register {
    private final Assortment assortment;
    private final ReceiptLedger ledger;
    private ShoppingCart cart;
    private final Scanner scanner;

    private boolean scanningCompleted;

    public Register(Assortment assortment, ReceiptLedger ledger, Scanner scanner) {
        this.assortment = assortment;
        this.ledger = ledger;
        this.scanner = scanner;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void initializePurchase() {
        cart = new ShoppingCart();
        scanner.initialize();
    }

    public boolean addToCart(String ean) {
        canModifyCart();
        Optional<Item> itemOptional = assortment.getItem(ean);

        return itemOptional.filter(item -> cart.addItem(item)).isPresent();

    }

    public boolean scanToAdd() {
        if (scanner.isConnected()) {
            String ean = scanner.getEAN();
            return addToCart(ean);
        }
        return false;
    }

    public boolean removeFromCart(String ean) {
        canModifyCart();
        Optional<Item> itemOptional = assortment.getItem(ean);
        return itemOptional.filter(item -> cart.removeItem(item)).isPresent();

    }

    private void canModifyCart() {
        if (scanningCompleted) {
            throw new IllegalStateException("Scanning is completed");
        }

        if (cart == null) {
            throw new IllegalStateException("Shoppingcart not initialized");
        }
    }

    public void cancelPurchase() {
        cart = null;
    }


    public boolean getScanningCompleted() {
        return scanningCompleted;
    }

    public void setScanningCompleted(boolean scanningCompleted) {
        this.scanningCompleted = scanningCompleted;
    }

    public void pay() {
        if (cart.isEmpty()) {
            return;
        }
        if (getScanningCompleted()) {
            // Needs additional functionality
            // Receive payment not impl
            cart.setPaid(true);
        }
    }

    // create receipt
    public Receipt createReceipt() {
        return new Receipt(cart.getLineItemsForPaidPurchase());
    }

    // TODO print receipt

    // log receipt
    public boolean logReceipt(Receipt receipt) {
        return ledger.add(receipt);
    }

    // complete purchase





}
