public class LineItem {

    private static final int MAXIMUM_QUANTITY_PER_ITEM = 100;
    private static final int ONE = 1;
    private final Item item;
    private int quantity;

    public LineItem(Item item, int quantity) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        if (quantity < ONE) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }

        if ((quantity > MAXIMUM_QUANTITY_PER_ITEM)) {
            throw new IllegalArgumentException("We are not a wholesale business! Maximum 100 units of every item.");
        }

        this.quantity = quantity;
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantityByOne() {
        if(quantity <MAXIMUM_QUANTITY_PER_ITEM) {
            this.quantity++;
        }else{
            throw new IllegalArgumentException("We are not a wholesale business! Maximum 100 units of every item.");
        }
    }

    public void decreaseQuantityByOne() {
        if(quantity>0) {
            this.quantity--;
        }else{
            throw new IllegalArgumentException("Quantity can't decrease below 0");
        }
    }

    public long getPrice() {
        return item.getPrice() * quantity;
    }
//    public long getVat() {
//        return item.getVAT() * quantity;
//    }

    public Item getItem() {
        return item;
    }
}
