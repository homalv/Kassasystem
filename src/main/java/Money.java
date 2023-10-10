public class Money {

    private final long value;

    public Money(long value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value cannot be negative");
        }
        this.value = value;
    }


    public long getValue() {
        return value;
    }
}
