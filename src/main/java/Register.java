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

        // cart.addItem() should ret boolean
        cart.addItem(itemOptional.get());
        return true;
    }

    public boolean removeFromCart(long ean) {
        Optional<Item> itemOptional = assortment.getItem(ean);
        if (itemOptional.isEmpty()) {
            return false;
        }

        // cart.removeItem() should ret boolean
        cart.removeItem(itemOptional.get());
        return true;
    }
}
