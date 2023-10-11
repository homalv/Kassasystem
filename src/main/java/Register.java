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

}
