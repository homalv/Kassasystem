public class Item {

    private final long price;
    private final String name;

    public Item(String name, long price) {
        this.name = name;
        this.price = price;
    }

    public long getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
