public class ShoppingCartAdapter implements ShoppingCartInterface {

    public final ShoppingCart shoppingCart;

    public ShoppingCartAdapter() {
        shoppingCart = new ShoppingCart();
    }

    @Override
    public boolean removeFromCart(long ean) {
        return false;
    }
}
