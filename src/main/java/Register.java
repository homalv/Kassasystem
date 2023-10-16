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

        return cart.addItem(itemOptional.get());
    }

    public boolean removeFromCart(long ean) {
        Optional<Item> itemOptional = assortment.getItem(ean);
        if (itemOptional.isEmpty()) {
            return false;
        }

        return cart.removeItem(itemOptional.get());
    }
}
