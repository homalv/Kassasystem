import java.util.Optional;

public class Register {
    private final Assortment assortment;
    private ShoppingCart cart;


    public Register(Assortment assortment) {
        this.assortment = assortment;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void initializePurchase() {
        cart = new ShoppingCart();
    }

    public boolean addToCart(long ean) {
        Optional<Item> itemOptional = assortment.getItem(ean);

        if (itemOptional.isEmpty()) {
            return false;
        }

        Item item = itemOptional.get();

        LineItem lineItem = new LineItem(item, 1);

        cart.addItem(item);
        return true;
    }
}
