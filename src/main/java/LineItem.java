public record LineItem(Item item, int quantity) {

    public LineItem {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }
    }
}
