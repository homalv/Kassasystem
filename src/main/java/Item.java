public class Item {

    private final Money price;
    private final String name;

    public Item(String name, Money price) {
        this.name = name;
        this.price = price;
    }

    public Money getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
