public class LineItem {

    private final Item item;
    private int quantity;

    public LineItem(Item item, int quantity) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }

        this.quantity = quantity;
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantityByOne() {
        this.quantity++;
    }

    public void decreaseQuantityByOne() {
        this.quantity--;
    }

    public long getPrice() {
        return item.getPrice() * quantity;
    }
    public long getVat() {
        return item.getVAT() * quantity;
    }

    public Item getItem() {
        return item;
    }
}
