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
        if (cart == null) {
            throw new IllegalStateException("Shoppingcart not initialized");
        }
        Optional<Item> itemOptional = assortment.getItem(ean);

        if (itemOptional.isEmpty()) {
            return false;
        }

        return cart.addItem(itemOptional.get());
    }

    public boolean removeFromCart(long ean) {
        if (cart == null) {
            throw new IllegalStateException("Shoppingcart not initialized");
        }
        Optional<Item> itemOptional = assortment.getItem(ean);
        if (itemOptional.isEmpty()) {
            return false;
        }

        return cart.removeItem(itemOptional.get());
    }


    public void cancelPurchase() {
        cart = null;
    }
}
